package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.common.utils.ParamUtils;
import org.example.jfinal.system.model.PermissionGroup;
import org.example.jfinal.system.service.PermissionGroupService;

import java.util.List;
import java.util.UUID;

public class PermissionGroupServiceImpl extends BaseService<PermissionGroup> implements PermissionGroupService {
    @Override
    public List<PermissionGroup> getAll(String service) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "service = ", service);
        return getAllByParam(new PermissionGroup(), "permissionGroup.getByParam", param);
    }

    @Override
    public Page<PermissionGroup> getList(Integer pageNumber, Integer pageSize, String service) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "service = ", service);
        return getListByParam(new PermissionGroup(), pageNumber, pageSize, "permissionGroup.getByParam", param);
    }

    @Override
    public void add(PermissionGroup permissionGroup) {
        permissionGroup.setUuid(UUID.randomUUID().toString());
        permissionGroup.save();
    }

    @Override
    public void update(PermissionGroup newPermissionGroup) {
        PermissionGroup permissionGroup = getFirstByUuid(new PermissionGroup(), "permissionGroup.getByParam", newPermissionGroup.getUuid());
        if (permissionGroup == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "规则组");
        }
        newPermissionGroup.update();
    }

    @Override
    public void delete(String uuid) {
        PermissionGroup permissionGroup = getFirstByUuid(new PermissionGroup(), "permissionGroup.getByParam", uuid);
        if (permissionGroup == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "规则组");
        }
        permissionGroup.delete();
    }

    @Override
    public PermissionGroup get(String uuid) {
        return getFirstByUuid(new PermissionGroup(), "permissionGroup.getByParam", uuid);
    }
}
