package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.service.UserRoleService;
import org.example.jfinal.system.service.impl.UserRoleServiceImpl;

import java.util.List;

@Path("/userRole")
public class UserRoleController extends BaseController {

    @Inject(UserRoleServiceImpl.class)
    private UserRoleService userRoleService;


    @Auth(value = "userRole.save", name = "授权用户角色")
    public void save(@Para("userUuid") String userUuid, @Para("roles") List<String> roles) {
        renderJson(Result.ofSuccess(null, "授权用户角色成功"));
    }
}
