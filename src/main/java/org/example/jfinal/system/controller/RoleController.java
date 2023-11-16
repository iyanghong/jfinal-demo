package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.service.RoleService;
import org.example.jfinal.system.service.impl.RoleServiceImpl;

import java.util.List;

@Path("/role")
public class RoleController extends BaseController {

    @Inject(RoleServiceImpl.class)
    private RoleService roleService;


    @Auth(value = "role.getAll", name = "获取所有角色")
    public void getAll(@Para("name") String name) {
        renderJson(Result.ofSuccess(roleService.getAll(name)));
    }

    @Auth(value = "role.getUserRole", name = "获取用户角色列表")
    public void getUserRole(@Para("userUuid") String userUuid) {
        renderJson(Result.ofSuccess(roleService.getUserRole(userUuid)));
    }

    @Auth(value = "role.getList", name = "获取角色列表")
    public void getList(@Para("name") String name) {
        renderJson(Result.ofSuccess(roleService.getList(getPageNumberParam(), getPageSizeParam(), name)));
    }

    @Auth(value = "role.add", name = "新增角色")
    public void add(@Para("") Role role) {
        roleService.add(role);
        renderJson(Result.ofSuccess(null, "新增成功"));
    }

    @Auth(value = "role.update", name = "修改角色")
    public void update(@Para("") Role role) {
        roleService.update(role);
        renderJson(Result.ofSuccess(null, "修改成功"));
    }

    @Auth(value = "role.delete", name = "删除角色")
    public void delete(@Para("uuid") String uuid) {
        roleService.delete(uuid);
        renderJson(Result.ofSuccess(null, "删除成功"));
    }

    @Auth(value = "role.detail", name = "获取角色详情")
    public void detail(@Para("uuid") String uuid) {
        renderJson(Result.ofSuccess(roleService.detail(uuid)));
    }
}
