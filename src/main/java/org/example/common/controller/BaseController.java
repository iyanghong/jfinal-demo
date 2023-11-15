package org.example.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang3.StringUtils;
import org.example.Constant;
import org.example.common.utils.JwtUtil;
import org.example.jfinal.system.model.Permission;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.vo.OnlineUser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController extends Controller {
    protected OnlineUser getCurrentUser() {
        //从 http 请求头中取出 token
        String token = getRequest().getHeader("token");
        //验证 token
        if (!JwtUtil.checkSign(token)){
            return null;
        }
        Map<String,Object> info = JwtUtil.getInfo(token);
        if (info == null) return null;
        String uuid = String.valueOf(info.get("uuid"));
        return (OnlineUser)  Redis.use().get(Constant.ONLINE_USER_CACHE_KEY + uuid);
    }
}
