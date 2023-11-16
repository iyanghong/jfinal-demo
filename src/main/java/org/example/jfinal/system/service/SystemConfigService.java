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
    Page<SystemConfig> getList(Integer pageNumber, Integer pageSize, String type, String enable);
    void updateConfig(SystemConfig newSystemConfig);

    /**
     * 根据code获取配置值
     *
     * @param code
     * @return
     */
    SystemConfig getByCode(String code);
    void delete(String uuid);

    /**
     * 添加系统配置
     *
     * @param newSystemConfig
     */
    void addConfig(SystemConfig newSystemConfig);
}
