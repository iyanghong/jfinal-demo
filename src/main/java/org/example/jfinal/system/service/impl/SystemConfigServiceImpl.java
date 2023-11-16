package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang3.StringUtils;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.common.utils.CacheRememberUtil;
import org.example.jfinal.system.model.SystemConfig;
import org.example.jfinal.system.service.SystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SystemConfigServiceImpl extends BaseService<SystemConfig> implements SystemConfigService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 获取配置列表
     *
     * @param pageNumber
     * @param pageSize
     * @param type
     * @param enable
     * @return
     */
    public Page<SystemConfig> getList(Integer pageNumber, Integer pageSize, String type, String enable) {
        Kv param = Kv.create();
        if (StringUtils.isNotBlank(type)) {
            param.set("type = ", type);
        }
        if (StringUtils.isNotBlank(enable)) {
            param.set("enable = ", enable);
        }
        return new SystemConfig().paginate(pageNumber, pageSize, Db.template("systemConfig.getByParam", Kv.of("param", param)).getSqlPara());
    }

    /**
     * 根据code获取配置值
     *
     * @param code
     * @return
     */
    public SystemConfig getByCode(String code) {
        return new CacheRememberUtil<SystemConfig>(code, 0, () -> {
            return new SystemConfig().findFirst(Db.template("systemConfig.getByCode", code).getSqlPara());
        }).get();
    }

    public void delete(String uuid) {
        SystemConfig systemConfig = getFirstByUuid(new SystemConfig(), "systemConfig.getByParam", uuid);
        if (systemConfig == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "系统配置");
        }
        systemConfig.delete();
        log.info("系统配置-删除系统配置成功，systemConfig={}", uuid);
    }

    /**
     * 添加系统配置
     *
     * @param newSystemConfig
     */
    public void addConfig(SystemConfig newSystemConfig) {
        SystemConfig systemConfig = new SystemConfig().findFirst(Db.template("systemConfig.getByCode", newSystemConfig.getCode()).getSqlPara());
        if (systemConfig != null) {
            throw new BaseException(ConstantException.PARAMETER_VERIFICATION_FAIL, "已存在同名Code");
        }
        newSystemConfig.setUuid(UUID.randomUUID().toString());
        newSystemConfig.save();
        log.info("系统配置-新增系统配置成功，code={}", newSystemConfig.getCode());
    }

    public void updateConfig(SystemConfig newSystemConfig) {
        SystemConfig systemConfig = getFirstByUuid(new SystemConfig(), "systemConfig.getByParam", newSystemConfig.getUuid());
        if (systemConfig == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "系统配置");
        }
        newSystemConfig.setId(systemConfig.getId());
        newSystemConfig.update();
        // 更新缓存
        Redis.use().set(systemConfig.getCode(), systemConfig.getValue());
        log.info("系统配置-修改系统配置成功,config={},code={}", systemConfig.getUuid(), systemConfig.getCode());
    }
}
