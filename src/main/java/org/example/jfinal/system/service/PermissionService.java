package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import org.example.jfinal.system.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getRolePermission(String roleUuid);

    List<Permission> getAll(String groupId, String type);

    Page<Permission> getList(Integer pageNumber, Integer pageSize, String groupId, String type);

    void add(Permission permission);

    void update(Permission permission);

    void delete(String uuid);

    Permission get(String uuid);
}
