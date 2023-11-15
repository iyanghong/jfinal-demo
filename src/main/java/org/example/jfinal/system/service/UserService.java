package org.example.jfinal.system.service;

import org.example.common.bean.Result;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.vo.OnlineUser;

public interface UserService {
    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    public OnlineUser login(String account, String password);
}
