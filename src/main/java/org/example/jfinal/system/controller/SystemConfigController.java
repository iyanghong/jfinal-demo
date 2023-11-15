package org.example.jfinal.system.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.example.common.bean.Result;
import org.example.common.controller.BaseController;
import org.example.jfinal.interceptor.Auth;
import org.example.jfinal.system.model.SystemConfig;
import org.example.jfinal.system.service.SystemConfigService;
import org.example.jfinal.system.service.impl.SystemConfigServiceImpl;
import sun.reflect.misc.ReflectUtil;

@Path("/systemConfig")
public class SystemConfigController extends BaseController {

    @Inject(SystemConfigServiceImpl.class)
    private SystemConfigService systemConfigService;


    public void getList() {
        Integer pageSize = getPageSizeParam();
        Integer pageNumber = getPageNumberParam();
        renderJson(new Result<>(systemConfigService.getList(pageNumber, pageSize, getPara("type"), getPara("enable"))));
    }

    @Auth(value = "systemConfig.updateConfig",name = "修改系统配置")
    public void updateConfig(@Para("") SystemConfig systemConfig) {
        systemConfigService.updateConfig(systemConfig);
        renderJson(Result.ofSuccess(null, "修改成功"));
    }
}
