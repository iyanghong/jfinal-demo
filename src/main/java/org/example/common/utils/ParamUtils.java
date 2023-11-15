package org.example.common.utils;

import com.jfinal.kit.Kv;
import org.apache.commons.lang3.StringUtils;

public class ParamUtils {
    public static void ifSetParam(Kv param, String key, String value) {
        if (StringUtils.isNotBlank(value)) param.set(key, value);
    }

    public static void ifSetParam(Kv param, String key, Integer value) {
        if (value != null) param.set(key, value);
    }
}
