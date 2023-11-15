package org.example.jfinal.interceptor;
import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {
    /**
     * 权限规则Value
     * @return
     */
    String value();

    /**
     * 名称
     * @return
     */
    String name();
}
