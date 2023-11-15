package org.example.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang3.StringUtils;
import org.example.Constant;
import org.example.common.bean.Result;
import org.example.common.exception.ConstantException;
import org.example.common.utils.JwtUtil;
import org.example.jfinal.system.vo.OnlineUser;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

public class AuthInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        Method method = invocation.getMethod();
        String methodName = invocation.getMethodName();
        Auth auth = method.getDeclaredAnnotation(Auth.class);

        if (auth != null) {
            System.out.println("value key: " + auth.value());
            Controller controller = invocation.getController();
            HttpServletRequest request = controller.getRequest();
            //从 http 请求头中取出 token
            String token = request.getHeader("token");
            //验证 token
            try {
                if (!JwtUtil.checkSign(token)) {
                    // 登录失
                    controller.renderJson(ConstantException.LOGIN_EXPIRES.getResult());
                    return;
                }
            }catch (Exception e){
                controller.renderJson(ConstantException.LOGIN_EXPIRES.getResult());
                return;
            }

            //验证通过后， 展示token有效信息
            String userId = JwtUtil.getUserId(token);
            Map<String, Object> info = JwtUtil.getInfo(token);
            if (info == null) {
                // 未登录
                controller.renderJson(ConstantException.NOT_LOGGED_IN.getResult());
                return;
            }
            String uuid = String.valueOf(info.get("uuid"));
            OnlineUser onlineUser = (OnlineUser) Redis.use().get(Constant.ONLINE_USER_CACHE_KEY + uuid);
            if (onlineUser == null) {
                // 未登录
                controller.renderJson(ConstantException.NOT_LOGGED_IN.getResult());
                return;
            }

            if (StringUtils.isNoneBlank(auth.value())) {
                if (!onlineUser.getPermissions().contains(auth.value())) {
                    // 无权限
                    controller.renderJson(
                            StringUtils.isBlank(auth.name()) ?
                                    ConstantException.NO_PERMISSION.getResult() :
                                    ConstantException.NO_PERMISSION_BY_PARAM.getResult(auth.name())
                    );
                    return;
                }
            }
        }
        invocation.invoke();
    }
}
