package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import org.example.jfinal.system.model.PermissionGroup;

import java.util.List;

public interface PermissionGroupService {

    List<PermissionGroup> getAll(String service);

    Page<PermissionGroup> getList(Integer pageNumber, Integer pageSize, String service);

    void add(PermissionGroup permissionGroup);

    void update(PermissionGroup permissionGroup);

    void delete(String uuid);

    PermissionGroup get(String uuid);
}
