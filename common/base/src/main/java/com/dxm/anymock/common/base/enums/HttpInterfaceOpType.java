package com.dxm.anymock.common.base.enums;

public enum HttpInterfaceOpType implements CodeBasedEnum {
    UPDATE(0),
    DELETE(1),
    START(2),
    SHUTDOWN(3);

    private final Integer code;

    HttpInterfaceOpType(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
