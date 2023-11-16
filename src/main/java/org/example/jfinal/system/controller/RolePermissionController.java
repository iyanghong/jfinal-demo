package org.example.jfinal.system.controller;


import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.service.RolePermissionService;
import org.example.jfinal.system.service.impl.RolePermissionServiceImpl;

import java.util.List;

@Path("/rolePermission")
public class RolePermissionController extends BaseController {

    @Inject(RolePermissionServiceImpl.class)
    private RolePermissionService rolePermissionService;

    @Auth(value = "rolePermission.save", name = "授权角色权限")
    public void save(@Para("roleUuid") String roleUuid, @Para("permissionList") List<String> permissionList) {
        rolePermissionService.save(roleUuid, permissionList);
        renderJson(Result.ofSuccess(null, "授权成功"));

    }
}
