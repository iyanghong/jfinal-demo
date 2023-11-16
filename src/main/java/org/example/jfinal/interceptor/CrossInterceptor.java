package org.example.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletResponse;

public class CrossInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        // TODO Auto-generated method stub

        if (inv.isActionInvocation()) {
            Controller c = inv.getController();
            HttpServletResponse response = c.getResponse();
            response.setHeader("Access-Control-Allow-Origin", "http://jfinal.yh.com");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "*,Token,token");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        inv.invoke();

    }

}