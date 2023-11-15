package org.example.jfinal.system.enums;

/**
 * 用户状态枚举
 */
public enum UserStatus {

    /**
     * 未激活
     */
    INACTIVATED(0, "未激活"),

    /**
     * 正常
     */
    AVAILABLE(1, "正常"),

    /**
     * 密码错误过多冻结
     */
    FREEZE(2, "冻结"),

    /**
     * 违规账户
     */
    VIOLATION(3, "违规"),

    /**
     * 注销账户
     */
    CANCEL_ACCOUNT(4, "注销");

    private final Integer status;
    private final String info;

    UserStatus(Integer status, String info) {
        this.status = status;
        this.info = info;
    }

    /**
     * 用户有效标识
     */
    public final static Integer[] ENABLE_STATUS = {UserStatus.INACTIVATED.status, UserStatus.AVAILABLE.status, UserStatus.FREEZE.status, UserStatus.VIOLATION.status};


    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
