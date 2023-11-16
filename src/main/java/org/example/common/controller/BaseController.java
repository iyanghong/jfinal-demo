package org.example.common.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang3.StringUtils;
import org.example.Constant;
import org.example.common.utils.JwtUtil;
import org.example.jfinal.system.vo.OnlineUser;

import java.util.Map;

public class BaseController extends Controller {


    protected OnlineUser getCurrentUser() {
        OnlineUser onlineUser = (OnlineUser) getRequest().getAttribute("onlineUser");
        if (onlineUser != null) return onlineUser;
        //从 http 请求头中取出 token
        String token = getRequest().getHeader("token");
        if (StringUtils.isBlank(token)) return null;
        //验证 token
        if (!JwtUtil.checkSign(token)) {
            return null;
        }
        Map<String, Object> info = JwtUtil.getInfo(token);
        if (info == null) return null;
        String uuid = String.valueOf(info.get("uuid"));
        return (OnlineUser) Redis.use().get(Constant.ONLINE_USER_CACHE_KEY + uuid);
    }

    protected Integer getPageNumberParam() {
        String pageNumber = getPara("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            return Integer.parseInt(pageNumber);
        }
        return Constant.DEFAULT_PAGE_NUMBER;
    }

    protected Integer getPageSizeParam() {
        String pageSize = getPara("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            return Integer.parseInt(pageSize);
        }
        return Constant.DEFAULT_PAGE_SIZE;
    }
}
