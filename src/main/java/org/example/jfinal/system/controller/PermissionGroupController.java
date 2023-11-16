package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.model.PermissionGroup;
import org.example.jfinal.system.service.PermissionGroupService;
import org.example.jfinal.system.service.impl.PermissionGroupServiceImpl;


@Path("/permissionGroup")
public class PermissionGroupController extends BaseController {

    @Inject(PermissionGroupServiceImpl.class)
    private PermissionGroupService permissionGroupService;


    @Auth(value = "permissionGroup.getAll", name = "获取所有权限组列表")
    public void getAll(@Para("service") String service) {
        renderJson(Result.ofSuccess(permissionGroupService.getAll(service)));
    }


    @Auth(value = "permissionGroup.getList", name = "获取权限组列表")
    public void getList(@Para("service") String service) {
        renderJson(Result.ofSuccess(permissionGroupService.getList(getPageNumberParam(), getPageSizeParam(), service)));
    }

    @Auth(value = "permissionGroup.add", name = "新增权限组")
    public void add(@Para("") PermissionGroup permissionGroup) {
        permissionGroupService.add(permissionGroup);
        renderJson(Result.ofSuccess(null, "新增成功"));
    }

    @Auth(value = "permissionGroup.update", name = "修改权限组")
    public void update(@Para("") PermissionGroup permissionGroup) {
        permissionGroupService.update(permissionGroup);
        renderJson(Result.ofSuccess(null, "修改成功"));
    }

    @Auth(value = "permissionGroup.delete", name = "删除权限组")
    public void delete(@Para("uuid") String uuid) {
        permissionGroupService.delete(uuid);
        renderJson(Result.ofSuccess(null, "删除成功"));
    }

    @Auth(value = "permissionGroup.getOne", name = "获取权限组")
    public void getOne(@Para("uuid") String uuid) {
        renderJson(Result.ofSuccess(permissionGroupService.get(uuid)));
    }
}
