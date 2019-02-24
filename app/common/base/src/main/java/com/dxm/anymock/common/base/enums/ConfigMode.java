package com.dxm.anymock.common.base.enums;

public enum ConfigMode implements CodeBasedEnum {
    STATIC(0),
    SCRIPT(1),
    SCRIPT_WITH_BRANCH(2);

    ConfigMode(Integer code) {
        this.code = code;
    }

    private final Integer code;

    @Override
    public Integer getCode() {
        return code;
    }
}
