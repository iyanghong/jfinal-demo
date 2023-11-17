package org.example.jfinal.system.service.impl;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.example.Constant;
import org.example.common.bean.Result;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.common.utils.JwtUtil;
import org.example.common.utils.ParamUtils;
import org.example.common.utils.RegularUtil;
import org.example.common.utils.StringEncryptUtil;
import org.example.jfinal.system.SystemConfigConstant;
import org.example.jfinal.system.config.ConfigConstant;
import org.example.jfinal.system.enums.UserStatus;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.model.UserStatusLog;
import org.example.jfinal.system.service.UserService;
import org.example.jfinal.system.service.UserStatusLogService;
import org.example.jfinal.system.vo.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class UserServiceImpl extends BaseService<User> {


    @Inject(UserStatusLogServiceImpl.class)
    private UserStatusLogService userStatusLogService;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    public OnlineUser login(String account, String password) {
        Date now = new Date();
        Kv param = Kv.create();
        // 是邮箱
        if (RegularUtil.isMatching(RegularUtil.REGULAR_EMAIL, account)) {
            param.set("email = ", account);
        } else {
            param.set("account = ", account);
        }

        User user = new User().findFirst(Db.template("user.getByParam", Kv.of("param", param)).getSqlPara());

        if (user == null) {
            throw new BaseException(ConstantException.ACCOUNT_NOT_EXIST);
        }


        if (!StringEncryptUtil.sha256Encrypt(password).equals(user.getPassword())) {
            if (user.getPasswordErrorNum() >= Integer.parseInt(SystemConfigConstant.get(SystemConfigConstant.USER_LOGIN_MAX_ERROR_NUM))) {
                if (userStatusLogService.abnormalStatusIsOver(user.getUuid()) == null) {
                    Date duration = DateUtils.addMinutes(now, 30);
                    userStatusLogService.logUserStatusChange(user.getUuid(), UserStatus.FREEZE.getStatus(), duration, "密码错误过多，已被冻结");
                    user.setStatus(UserStatus.FREEZE.getStatus());
                    user.update();
                    log.info("用户密码错误次数过多已被冻结，user={}", user.getUuid());
                    throw new BaseException(ConstantException.ACCOUNT_ERROR_NUM_MAX, String.valueOf(user.getPasswordErrorNum()));
                }
            }
            user.setPasswordErrorNum(user.getPasswordErrorNum() + 1);
            user.update();
            throw new BaseException(ConstantException.ACCOUNT_ERROR_NUM, String.valueOf(user.getPasswordErrorNum()));
        }

        // 状态异常
        if (user.getStatus() != UserStatus.AVAILABLE.getStatus()) {
            if (userStatusLogService.abnormalStatusIsOver(user.getUuid()) == null) {
                user.setStatus(UserStatus.AVAILABLE.getStatus());
                userStatusLogService.logUserStatusChange(user.getUuid(), UserStatus.AVAILABLE.getStatus(), now, "异常状态已到期");
            } else {
                String statusText = "";
                for (UserStatus us : UserStatus.values()) {
                    if (user.getStatus().equals(us.getStatus())) {
                        statusText = us.getInfo();
                        break;
                    }
                }
                throw new BaseException(ConstantException.ACCOUNT_ABNORMAL_STATUS, statusText);
            }
        }


        user.setPasswordErrorNum(0);
        user.update();


        //准备存放在JWT中的自定义数据
        DateFormat dft = new SimpleDateFormat("yyyyMMddHHmmss");
        String applyTime = dft.format(new Date());
        Map<String, Object> info = new HashMap<>();
        info.put("id", user.getId());
        info.put("uuid", user.getUuid());
        info.put("account", user.getAccount());
        info.put("email", user.getEmail());

        //生成JWT字符串
        String token = JwtUtil.sign(user.getId().toString(), info);
        List<Role> roles = getUserRoles(user.getUuid());
        List<Permission> permissionList = getUserPermission(user.getUuid());
        List<String> permissions = new ArrayList<>();
        if (!permissionList.isEmpty()) {
            permissionList.forEach((permission) -> permissions.add(permission.getValue()));
        }
        OnlineUser onlineUser = new OnlineUser(user, token, roles, permissions);
        Redis.use().psetex(Constant.ONLINE_USER_CACHE_KEY + user.getUuid(), JwtUtil.EXPIRE_TIME, onlineUser);
        return onlineUser;
    }

    /**
     * 获取用户的角色列表
     *
     * @param userUuid
     * @return
     */
    public List<Role> getUserRoles(String userUuid) {
        return new Role().find(Db.template("user.getUserRoles", userUuid).getSqlPara());
    }

    public List<Record> getUserRolesAdmin(String userUuid) {
        List<Record> list = Db.template("user.getUserRolesAdmin", userUuid).find();

        for (Record record : list) {
            record.set("checked", record.get("checked").equals("1"));
        }
        return list;
    }

    /**
     * 获取用户的权限列表
     *
     * @param userUuid
     * @return
     */
    public List<Permission> getUserPermission(String userUuid) {
        return new Permission().find(Db.template("user.getUserPermission", userUuid).getSqlPara());
    }


    /**
     * 获取用户列表
     *
     * @param pageNumber
     * @param pageSize
     * @param account
     * @param email
     * @param phone
     * @param gender
     * @param status
     * @return
     */
    public Page<User> getList(Integer pageNumber, Integer pageSize, String account, String email, String phone, String gender, String status) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "account = ", account);
        ParamUtils.ifSetParam(param, "email = ", email);
        ParamUtils.ifSetParam(param, "phone = ", phone);
        ParamUtils.ifSetParam(param, "gender = ", gender);
        ParamUtils.ifSetParam(param, "status = ", status);
        Page<User> page = getListByParam(new User(), pageNumber, pageSize, "user.getByParam", param);
        for (User user : page.getList()) {
            if ("90359963f3c04f528d7f2b1e1848cf36".equals(user.getDisk())) {
                user.setHeader("https://resources.iyanghong.cn/" + user.getHeader());
            } else if ("e650077ba2cc462f950bd929af822104".equals(user.getDisk())) {
                user.setHeader("https://resource.zzlcjj.xyz/" + user.getHeader());
            }
        }
        return page;
    }


    /**
     * 重置密码
     *
     * @param userUuid
     * @return
     */
    public void resetPassword(String userUuid) {
        User user = getUserByUuid(userUuid);
        user.setPassword(StringEncryptUtil.sha256Encrypt(ConfigConstant.DEFAULT_PASSWORD));
        user.update();
        log.info("用户管理，重置密码成功, user={}", user.getAccount());
    }


    /**
     * 修改密码
     *
     * @param userUuid
     * @param oldPassword
     * @param newPassword
     */
    public void updatePassword(String userUuid, String oldPassword, String newPassword) {
        User user = getUserByUuid(userUuid);
        if (!StringEncryptUtil.sha256Encrypt(oldPassword).equals(user.getPassword())) {
            log.error("修改密码, 旧密码错误: user={},account={}", user.getUuid(), user.getAccount());
            throw new BaseException(ConstantException.ACCOUNT_PASSWORD_ERROR);
        } else {
            user.setPassword(StringEncryptUtil.sha256Encrypt(newPassword));
            user.update();
            log.info("修改密码, 用户密码修改成功: user={},account={}", user.getUuid(), user.getAccount());
        }
    }

    /**
     * 更新当前登录用户数据
     *
     * @param uuid
     */
    public OnlineUser updateOnlineUser(String uuid) {
        OnlineUser onlineUser = Redis.use().get(Constant.ONLINE_USER_CACHE_KEY + uuid);
        if (onlineUser == null) return null;
        User user = getFirstByUuid(new User(), "user.getByParam", uuid);
        List<Role> roles = getUserRoles(user.getUuid());
        List<Permission> permissionList = getUserPermission(user.getUuid());
        List<String> permissions = new ArrayList<>();
        if (!permissionList.isEmpty()) {
            permissionList.forEach((permission) -> permissions.add(permission.getValue()));
        }
        onlineUser = new OnlineUser(user, onlineUser.getToken(), roles, permissions);
        Redis.use().psetex(Constant.ONLINE_USER_CACHE_KEY + user.getUuid(), JwtUtil.EXPIRE_TIME, onlineUser);
        return onlineUser;
    }

    private User getUserByUuid(String userUuid) {
        User user = getFirstByUuid(new User(), "user.getByParam", userUuid);
        if (user == null) {
            throw new BaseException(ConstantException.ACCOUNT_NOT_EXIST);
        }
        return user;
    }

    public void updateUserBaseData(OnlineUser onlineUser, String header, String nickname, String gender, String signature) {
        User user = getUserByUuid(onlineUser.getUuid());
        if (StringUtils.isNotBlank(header)) {
            user.setHeader(header);
        }
        if (StringUtils.isNotBlank(nickname)) {
            user.setNickname(nickname);
        }
        if (StringUtils.isNotBlank(gender)) {
            user.setGender(Integer.parseInt(gender));
        }
        if (StringUtils.isNotBlank(signature)) {
            user.setSignature(signature);
        }
        user.update();
        updateOnlineUser(user.getUuid());
    }

    public void update(User newUser) {
        if (getFirstByUuid(new User(), "user.getByParam", newUser.getUuid()) == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "用户");
        }
        if (StringUtils.isNotBlank(newUser.getEmail()) && checkEmailIsExist(newUser.getEmail(), newUser.getUuid())) {
            throw new BaseException(ConstantException.PARAMETER_VERIFICATION_FAIL, "该邮箱已被注册");
        }
        if (StringUtils.isNotBlank(newUser.getPhone()) && checkPhoneIsExist(newUser.getPhone(), newUser.getUuid())) {
            throw new BaseException(ConstantException.PARAMETER_VERIFICATION_FAIL, "该手机号已被注册");
        }
        newUser.update();
    }


    private boolean checkEmailIsExist(String email, String userUuid) {
        Kv param = Kv.create();
        param.set("email = ", email);
        ParamUtils.ifSetParam(param, "uuid != ", userUuid);
        User user = new User().findFirst(Db.template("user.getByParam", Kv.of("param", param)).getSqlPara());
        return user != null;
    }

    private boolean checkPhoneIsExist(String phone, String userUuid) {
        Kv param = Kv.create();
        param.set("phone = ", phone);
        ParamUtils.ifSetParam(param, "uuid != ", userUuid);
        User user = new User().findFirst(Db.template("user.getByParam", Kv.of("param", param)).getSqlPara());
        return user != null;
    }
}
