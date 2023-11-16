package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.model.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取所有角色
     *
     * @param name
     * @return
     */
    List<Role> getAll(String name);

    /**
     * 获取用户角色列表
     * @param userUuid
     * @return
     */
    List<Role> getUserRole(String userUuid);



    /**
     * 获取角色列表
     *
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    Page<Role> getList(Integer pageNumber, Integer pageSize, String name);

    /**
     * 新增角色
     *
     * @param role
     */
    void add(Role role);

    /**
     * 修改角色
     *
     * @param role
     */
    void update(Role role);

    void delete(String uuid);

    Role detail(String uuid);
}
