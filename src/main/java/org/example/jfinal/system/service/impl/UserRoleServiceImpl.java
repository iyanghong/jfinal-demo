package org.example.jfinal.system.service.impl;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import org.apache.commons.lang3.StringUtils;
import org.example.common.service.BaseService;
import org.example.jfinal.system.model.RolePermission;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.model.UserRole;
import org.example.jfinal.system.service.UserRoleService;
import org.example.jfinal.system.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {
    @Inject
    private UserServiceImpl userService;

    @Override
    public void save(String userUuid, String roles) {
        // 删除取消授权的
        SqlPara sqlPara = Db.template("userRole.deleteNeedDeleteList", userUuid, roles).getSqlPara();
        Db.delete(sqlPara.getSql(), userUuid, roles);

        List<UserRole> list = getAllByParam(new UserRole(), "userRole.getByParam", Kv.of("user = ", userUuid));
        List<String> roleList = new ArrayList<>();
        if (StringUtils.isNotBlank(roles)) {
            roleList = Arrays.asList(roles.split(","));
        }
        for (String roleUuid : roleList) {
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
        userService.updateOnlineUser(userUuid);
    }
}
