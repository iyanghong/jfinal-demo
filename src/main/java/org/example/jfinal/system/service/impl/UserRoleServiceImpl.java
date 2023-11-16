package org.example.jfinal.system.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import org.example.common.service.BaseService;
import org.example.jfinal.system.model.RolePermission;
import org.example.jfinal.system.model.UserRole;
import org.example.jfinal.system.service.UserRoleService;

import java.util.List;

public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {
    @Override
    public void save(String userUuid, List<String> roles) {
// 删除取消授权的
        Db.delete(Db.template("userRole.deleteNeedDeleteList", userUuid, roles).getSqlPara().getSql());

        List<UserRole> list = getAllByParam(new UserRole(), "userRole.getByParam", Kv.of("param", Kv.of("user", userUuid)));
        for (String roleUuid : roles) {
            boolean isExist = false;
            for (UserRole userRole : list) {
                if (userRole.getRole().equals(roleUuid)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                UserRole userRole = new UserRole();
                userRole.setRole(roleUuid);
                userRole.setUser(userUuid);
                userRole.save();
            }
        }
    }
}
