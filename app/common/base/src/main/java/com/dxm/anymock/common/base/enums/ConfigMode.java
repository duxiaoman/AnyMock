package com.dxm.anymock.common.base.enums;

public enum ConfigMode implements CodeBasedEnum {
    STATIC(1),
    SCRIPT(2),
    SCRIPT_WITH_BRANCH(3);

    ConfigMode(Integer code) {
        this.code = code;
    }

    private final Integer code;

    @Override
    public Integer getCode() {
        return code;
    }
}
