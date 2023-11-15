package org.example.jfinal.system;

import org.example.jfinal.system.service.impl.SystemConfigServiceImpl;

public class SystemConfigConstant {



    public static String get(String code) {
        SystemConfigServiceImpl systemConfigService = new SystemConfigServiceImpl();
        return systemConfigService.getByCode(code).getValue();
    }

    /**
     * 用户头像最大内存
     */
    public static final String USER_HEADER_MAX_SIZE = "sys:config:user:headerMaxSize";

    /**
     * 用户允许上传的头像类型
     */
    public static final String USER_HEADER_TYPES = "sys:config:user:headerType";

    /**
     * 用户登陆最大密码连续错误次数
     */
    public static final String USER_LOGIN_MAX_ERROR_NUM = "sys:config:user:passwordMaxErrorNum";

    /**
     * 邮件模板默认占位值
     */
    public static final String EMAIL_TEMPLATE_PLACEHOLDER = "sys:config:user:templatePlaceholder";

    /**
     * 邮件模板默认占位值
     */
    public static final String DEFAULT_ACTIVATION_EMAIL_TEMPLATE = "sys:config:user:defaultActivationEmail";

    /**
     * 用户默认角色
     */
    public static final String USER_DEFAULT_ROLE = "sys:config:user:defaultRole";

    /**
     * 用户默认密码
     */
    public static final String USER_DEFAULT_PASSWORD = "sys:config:user:defaultPassword";
}
