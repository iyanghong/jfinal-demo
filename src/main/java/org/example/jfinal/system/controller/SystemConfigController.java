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

    @Auth(value = "systemConfig.getList", name = "获取系统配置列表")
    public void getList() {
        Integer pageSize = getPageSizeParam();
        Integer pageNumber = getPageNumberParam();
        renderJson(new Result<>(systemConfigService.getList(pageNumber, pageSize, getPara("type"), getPara("enable"))));
    }

    @Auth(value = "systemConfig.updateConfig", name = "修改系统配置")
    public void updateConfig(@Para("") SystemConfig systemConfig) {
        systemConfigService.updateConfig(systemConfig);
        renderJson(Result.ofSuccess(null, "修改成功"));
    }

    @Auth(value = "systemConfig.add", name = "新增系统配置")
    public void add(@Para("") SystemConfig systemConfig) {
        systemConfigService.addConfig(systemConfig);
        renderJson(Result.ofSuccess(null, "新增成功"));
    }

    @Auth(value = "systemConfig.delete", name = "删除系统配置")
    public void delete(@Para("uuid") String uuid) {
        systemConfigService.delete(uuid);
        renderJson(Result.ofSuccess(null, "删除成功"));
    }

    @Auth(value = "systemConfig.getByCode", name = "根据code获取系统配置")
    public void getByCode(@Para("code") String code) {
        renderJson(Result.ofSuccess(systemConfigService.getByCode(code)));
    }
}
