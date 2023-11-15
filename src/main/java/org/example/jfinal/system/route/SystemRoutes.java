package org.example.jfinal.system.route;

import com.jfinal.config.Routes;
import org.example.jfinal.system.controller.SystemConfigController;
import org.example.jfinal.system.controller.UserController;

public class SystemRoutes extends Routes {
    @Override
    public void config() {
        add("user", UserController.class);
        add("systemConfig", SystemConfigController.class);
    }
}
