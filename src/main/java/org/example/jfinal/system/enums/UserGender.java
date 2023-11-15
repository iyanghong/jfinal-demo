package org.example.jfinal.system.enums;

public enum UserGender {

    /**
     * 未激活
     */
    UNKNOWN(0, "未知"),

    /**
     * 男性
     */
    MALE(1, "男"),

    /**
     * 女性
     */
    FEMALE(2, "女");

    private final Integer code;
    private final String info;

    UserGender(Integer status, String info) {
        this.code = status;
        this.info = info;
    }

    public static String getGender(Integer code) {
        if (code.equals(UNKNOWN.code)){
            return UNKNOWN.info;
        } else if (code.equals(MALE.code)){
            return MALE.info;
        } else {
            return FEMALE.info;
        }
    }
}
