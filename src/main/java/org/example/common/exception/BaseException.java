package org.example.common.exception;

import org.example.common.bean.Result;

/**
 * 系统的自定义异常类
 */
public class BaseException extends RuntimeException {

    /**
     * 错误状态码
     */
    private final Integer code;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ConstantException constantException) {
        super(constantException.msg);
        this.code = constantException.code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public Result<String> getResult() {
        return Result.ofFail(getCode(), getMessage());
    }
    public Result<String> getResult(String ...params) {
        String message = getMessage();
        for (String p : params) {
            message = message.replace("{}",p);
        }
        return Result.ofFail(code,message);
    }
}
