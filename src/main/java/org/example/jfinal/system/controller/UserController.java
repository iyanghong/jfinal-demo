package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.apache.commons.lang3.StringUtils;
import org.example.common.controller.BaseController;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.utils.CacheRememberUtil;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.service.UserService;
import org.example.jfinal.system.service.impl.UserServiceImpl;
import org.example.jfinal.system.vo.OnlineUser;

import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserController extends BaseController {

    @Inject
    private UserServiceImpl userService;

    public void index() {
        renderText("user.index");
    }

    public void login() {
        String account = getPara("account");
        String password = getPara("password");
        if (StringUtils.isBlank(account)) {
            renderJson(Result.ofFail(1, "账号不能为空"));
            return;
        }

        if (StringUtils.isBlank(password)) {
            renderJson(Result.ofFail(1, "密码不能为空"));
            return;
        }
        renderJson(new Result<OnlineUser>(userService.login(account, password), "登录成功"));
    }

    public void getUserSession() {
        OnlineUser onlineUser = getCurrentUser();
        if (onlineUser == null) {
            renderJson(new Result<OnlineUser>(1, "未登录"));
        } else {
            renderJson(new Result<OnlineUser>(onlineUser, "已登录"));
        }
    }

    @Auth(value = "user.list", name = "获取用户列表")
    public void list(@Para("account") String account, @Para("email") String email, @Para("phone") String phone, @Para("gender") String gender, @Para("status") String status) {
        renderJson(Result.ofSuccess(userService.getList(getPageNumberParam(), getPageSizeParam(), account, email, phone, gender, status)));
    }

    @Auth(value = "user.resetPassword", name = "重置用户密码")
    public void resetPassword(@Para("userUuid") String userUuid) {
        userService.resetPassword(userUuid);
        renderJson(Result.ofSuccess(null, "重置密码成功"));
    }


    public void updatePassword(@Para("oldPassword") String oldPassword, @Para("newPassword") String newPassword) {
        OnlineUser onlineUser = getCurrentUser();
        if (onlineUser == null) {
            renderJson(ConstantException.NOT_LOGGED_IN.getResult());
            return;
        }
        userService.updatePassword(onlineUser.getUuid(), oldPassword, newPassword);
        renderJson(Result.ofSuccess(null, "修改密码成功"));
    }


    /**
     * 修改基本信息
     *
     * @param header
     * @param nickname
     * @param gender
     * @param signature
     */
    public void updateUserBaseData(@Para("header") String header, @Para("nickname") String nickname, @Para("gender") String gender, @Para("signature") String signature) {
        OnlineUser onlineUser = getCurrentUser();
        if (onlineUser == null) {
            renderJson(ConstantException.NOT_LOGGED_IN.getResult());
            return;
        }
        userService.updateUserBaseData(onlineUser, header, nickname, gender, signature);
        renderJson(Result.ofSuccess(null, "修改信息成功"));
    }
}
