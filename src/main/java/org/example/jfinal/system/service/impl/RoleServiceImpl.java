package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.common.utils.ParamUtils;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.service.RoleService;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl extends BaseService<Role> implements RoleService {
    @Override
    public List<Role> getAll(String name) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "name = ", name);
        return getAllByParam(new Role(), "role.getByParam", param);
    }

    @Override
    public List<Role> getUserRole(String userUuid) {
        return new Role().find(Db.template("role.getUserRole",userUuid).getSqlPara());
    }

    @Override
    public Page<Role> getList(Integer pageNumber, Integer pageSize, String name) {
        Kv param = Kv.create();
        ParamUtils.ifSetParam(param, "name = ", name);
        return getListByParam(new Role(), pageNumber, pageSize, "role.getByParam", param);

    }

    @Override
    public void add(Role role) {
        role.setUuid(UUID.randomUUID().toString());
        role.save();
    }

    @Override
    public void update(Role newRole) {
        Role role = getFirstByUuid(new Role(), "role.getByParam", newRole.getUuid());
        if (role == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "角色");
        }
        newRole.update();
    }

    @Override
    public void delete(String uuid) {
        Role role = getFirstByUuid(new Role(), "role.getByParam", uuid);
        if (role == null) {
            throw new BaseException(ConstantException.DATA_NOT_FOUND, "角色");
        }
        role.delete();
    }

    @Override
    public Role detail(String uuid) {
        return getFirstByUuid(new Role(), "role.getByParam", uuid);
    }
}
