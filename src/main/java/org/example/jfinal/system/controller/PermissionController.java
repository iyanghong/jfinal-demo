package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.service.PermissionService;
import org.example.jfinal.system.service.impl.PermissionServiceImpl;


@Path("/permission")
public class PermissionController extends BaseController {
    @Inject(PermissionServiceImpl.class)
    private PermissionService permissionService;


    @Auth(value = "permission.getAll", name = "获取所有权限规则列表")
    public void getAll(@Para("groupId") String groupId, @Para("type") String type) {
        renderJson(Result.ofSuccess(permissionService.getAll(groupId, type)));
    }

    @Auth(value = "permission.getList", name = "获取权限规则列表")
    public void getList(@Para("groupId") String groupId, @Para("type") String type) {
        renderJson(Result.ofSuccess(permissionService.getList(getPageNumberParam(), getPageSizeParam(), groupId, type)));
    }

    @Auth(value = "permission.add", name = "新增权限规则")
    public void add(@Para("") Permission permission) {
        permissionService.add(permission);
        renderJson(Result.ofSuccess(null, "新增成功"));
    }

    @Auth(value = "permission.update", name = "修改权限规则")
    public void update(@Para("") Permission permission) {
        permissionService.update(permission);
        renderJson(Result.ofSuccess(null, "修改成功"));
    }

    @Auth(value = "permission.delete", name = "删除权限规则")
    public void delete(@Para("uuid") String uuid) {
        permissionService.delete(uuid);
        renderJson(Result.ofSuccess(null, "删除成功"));
    }

    @Auth(value = "permission.getOne", name = "获取权限规则")
    public void getOne(@Para("uuid") String uuid) {
        renderJson(Result.ofSuccess(permissionService.get(uuid)));
    }
}
