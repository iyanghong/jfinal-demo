package org.example.jfinal.system.service.impl;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import org.example.Constant;
import org.example.common.bean.Result;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.utils.JwtUtil;
import org.example.common.utils.RegularUtil;
import org.example.common.utils.StringEncryptUtil;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.service.UserService;
import org.example.jfinal.system.vo.OnlineUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class UserServiceImpl implements UserService {

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    public OnlineUser login(String account, String password) {
        Result<User> result = new Result<>();
        Kv param = Kv.create();
        // 是邮箱
        if (RegularUtil.isMatching(RegularUtil.REGULAR_EMAIL, account)) {
            param.set("email = ", account);
        } else {
            param.set("account = ", account);
        }

        User user = new User().findFirst(Db.template("login", Kv.of("param", param)).getSqlPara());

        if (user == null) {
            throw new BaseException(ConstantException.ACCOUNT_NOT_EXIST);
        }
        if (!StringEncryptUtil.sha256Encrypt(password).equals(user.getPassword())) {
            throw new BaseException(ConstantException.ACCOUNT_PASSWORD_ERROR);
        }

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
        return new Role().find(Db.template("getUserRoles", userUuid).getSqlPara());
    }

    /**
     * 获取用户的权限列表
     *
     * @param userUuid
     * @return
     */
    public List<Permission> getUserPermission(String userUuid) {
        return new Permission().find(Db.template("getUserPermission", userUuid).getSqlPara());
    }

}
