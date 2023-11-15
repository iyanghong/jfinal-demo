package org.example.common.exception;

import org.example.common.bean.Result;

import java.util.Arrays;
import java.util.List;

/**
 * 定义系统异常的错误码和提示
 */
public class ConstantException {

    public final Integer code;
    public final String msg;

    public ConstantException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result<String> getResult() {
        return Result.ofFail(code,msg);
    }

    public Result<String> getResult(String ...params) {
        String message = msg;
        for (String p : params) {
            message = message.replace("{}",p);
        }
        return Result.ofFail(code,message);
    }

    public static ConstantException ACCOUNT_NOT_EXIST = new ConstantException(1101, "账号不存在");
    public static ConstantException ACCOUNT_PASSWORD_ERROR = new ConstantException(1102, "密码错误");
    public static ConstantException NOT_LOGGED_IN = new ConstantException(2001, "未登录");
    public static ConstantException LOGIN_EXPIRES = new ConstantException(2002, "登录失效");
    public static ConstantException NO_PERMISSION = new ConstantException(2003, "无权限");
    public static ConstantException NO_PERMISSION_BY_PARAM = new ConstantException(2003, "无{}权限");

}
