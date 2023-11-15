package org.example.common.bean;

import java.io.Serializable;

public class Result<T> implements Serializable {

    /**
     * 返回状态代码
     */
    private int code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 提示消息
     */
    private String message;

    public Result() {
        this.message = "操作成功";
    }

    public Result(int code) {
        this(0, null, null);
    }

    public Result(T data) {
        this(0, data, "操作成功");
    }

    public Result(T data, String message) {
        this(0, data, message);
    }

    public Result(int code, String message) {
        this(code, null, message);
    }

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static Result<String> ofMsg(String msg) {
        return new Result<>(0, null, msg);
    }

    public static Result<String> ofFail(int code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static Result<Object> ofSuccess(Object data) {
        return new Result<>(0, data, "ok");
    }

    public static Result<Object> ofSuccess(String message, Object data) {
        return new Result<>(0, data, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
