package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import org.example.common.service.BaseService;
import org.example.jfinal.system.model.RolePermission;
import org.example.jfinal.system.service.RolePermissionService;

import java.util.List;

public class RolePermissionServiceImpl extends BaseService<RolePermission> implements RolePermissionService {
    @Override
    public void save(String roleUuid, List<String> permissionList) {
        // 删除取消授权的
        Db.delete(Db.template("rolePermission.deleteNeedDeleteList", roleUuid, permissionList).getSqlPara().getSql());

        List<RolePermission> list = getAllByParam(new RolePermission(), "rolePermission.getByParam", Kv.of("param", Kv.of("role", roleUuid)));
        for (String permissionUuid : permissionList) {
            boolean isExist = false;
            for (RolePermission rolePermission : list) {
                if (rolePermission.getPermission().equals(permissionUuid)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRole(roleUuid);
                rolePermission.setPermission(permissionUuid);
                rolePermission.save();
            }
        }
    }
}
