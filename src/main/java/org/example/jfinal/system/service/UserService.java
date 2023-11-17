package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.vo.OnlineUser;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    OnlineUser login(String account, String password);

    /**
     * 获取用户列表
     *
     * @param pageNumber
     * @param pageSize
     * @param account
     * @param email
     * @param phone
     * @param gender
     * @param status
     * @return
     */
    Page<User> getList(Integer pageNumber, Integer pageSize, String account, String email, String phone, String gender, String status);

    void update(User newUser);
    List<Role> getUserRoles(String userUuid);
    List<Record> getUserRolesAdmin(String userUuid);
    List<Permission> getUserPermission(String userUuid);
    void resetPassword(String userUuid);
    void updatePassword(String userUuid, String oldPassword, String newPassword);
    OnlineUser updateOnlineUser(String uuid);
}