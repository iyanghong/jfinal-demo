package org.example.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.example.common.bean.Result;
import org.example.common.exception.BaseException;

public class ExceptionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        try {
            inv.invoke();
        } catch (BaseException baseException) {
            inv.getController().renderJson(baseException.getResult());
        } catch (Exception e) {
            inv.getController().renderJson(Result.ofFail(500, e.getMessage()));
            e.printStackTrace();
        }
    }
}
