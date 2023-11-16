package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.common.utils.ParamUtils;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.service.PermissionService;

import java.util.List;
import java.util.UUID;

public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {
    @Override
    public List<Permission> getRolePermission(String roleUuid) {

        return null;
    }

    @Override
    public List<Permission> getAll(String groupId, String type) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "group_id = ", groupId);
        ParamUtils.ifSetParam(param, "type = ", type);
        return getAllByParam(new Permission(), "permission.getByParam", param);
    }

    @Override
    public Page<Permission> getList(Integer pageNumber, Integer pageSize, String groupId, String type) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "group_id = ", groupId);
        ParamUtils.ifSetParam(param, "type = ", type);
        return getListByParam(new Permission(), pageNumber, pageSize, "permission.getByParam", param);

    }

    @Override
    public void add(Permission permission) {
        permission.setUuid(UUID.randomUUID().toString());
        permission.save();
    }

    @Override
    public void update(Permission newPermission) {
        Permission permission = getFirstByUuid(new Permission(), "permission.getByParam", newPermission.getUuid());
        if (permission == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "权限规则");
        }
        newPermission.save();
    }

    @Override
    public void delete(String uuid) {
        Permission permission = getFirstByUuid(new Permission(), "permission.getByParam", uuid);
        if (permission == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "权限规则");
        }
        permission.delete();

    }

    @Override
    public Permission get(String uuid) {
        return getFirstByUuid(new Permission(), "permission.getByParam", uuid);
    }
}
