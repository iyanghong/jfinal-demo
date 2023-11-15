package org.example.jfinal.system.service;

import com.jfinal.plugin.activerecord.Page;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.vo.OnlineUser;

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
}