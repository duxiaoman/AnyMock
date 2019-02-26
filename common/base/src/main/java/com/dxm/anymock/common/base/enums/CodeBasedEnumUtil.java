package com.dxm.anymock.common.base.enums;

public class CodeBasedEnumUtil {
    public static <T, S extends CodeBasedEnum<T>> S convertByCode(Class<S> targetEnum, T code) {
        if (null == code || null == targetEnum) {
            return null;
        }

        S[] values = targetEnum.getEnumConstants();
        for (S v : values) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }
        return null;
    }
}
