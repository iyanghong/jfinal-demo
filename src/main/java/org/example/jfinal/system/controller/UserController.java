package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import org.example.common.bean.Result;
import org.apache.commons.lang3.StringUtils;
import org.example.common.controller.BaseController;
import org.example.common.exception.BaseException;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.service.UserService;
import org.example.jfinal.system.service.impl.UserServiceImpl;
import org.example.jfinal.system.vo.OnlineUser;

@Path("/user")
public class UserController extends BaseController {

    @Inject(UserServiceImpl.class)
    private UserService userService;

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
        try {
            renderJson(new Result<OnlineUser>(userService.login(account, password), "登录成功"));
        } catch (BaseException baseException) {
            renderJson(baseException.getResult());
        }

    }

    public void getUserSession() {
        OnlineUser onlineUser = getCurrentUser();
        if (onlineUser == null) {
            renderJson(new Result<OnlineUser>(1, "未登录"));
        } else {
            renderJson(new Result<OnlineUser>(onlineUser, "已登录"));
        }
    }

    @Auth(value = "user.list",name = "获取用户列表")
    public void list() {

    }
}
