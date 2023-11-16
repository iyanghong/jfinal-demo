package org.example.jfinal.system.service;

import java.util.List;

public interface RolePermissionService {
    void save(String roleUuid, List<String> permissionList);
}
