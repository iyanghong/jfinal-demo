package org.example.jfinal.system.route;

import com.jfinal.config.Routes;
import org.example.jfinal.system.controller.*;

public class SystemRoutes extends Routes {
    @Override
    public void config() {
        add("user", UserController.class);
        add("systemConfig", SystemConfigController.class);
        add("permission", PermissionController.class);
        add("permissionGroup", PermissionGroupController.class);
        add("role", RoleController.class);
        add("rolePermission", RolePermissionController.class);
        add("userRole", UserRoleController.class);
    }
}
