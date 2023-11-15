package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import org.example.jfinal.system.model.SystemConfig;

public interface SystemConfigService {
    /**
     * 获取配置列表
     * @param pageNumber
     * @param pageSize
     * @param type
     * @param enable
     * @return
     */
    public Page<SystemConfig> getList(Integer pageNumber, Integer pageSize, String type, String enable);
    void updateConfig(SystemConfig newSystemConfig);
}
