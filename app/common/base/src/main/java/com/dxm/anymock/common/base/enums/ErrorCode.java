package com.dxm.anymock.common.base.enums;

public enum ErrorCode implements CodeBasedEnum {
    ILLEGAL_ARGUMENT("100000"),

    SPACE_DUPLICATE_KEY("210001"),
    SUB_SPACE_DUPLICATE_KEY("210002"),
    HTTP_INTERFACE_DUPLICATE_KEY("210003"),
    HTTP_INTERFACE_BRANCH_SCRIPT_DUPLICATE_KEY("210004"),
    HTTP_INTERFACE_CALLBACK_REQUEST_HEADER_DUPLICATE_KEY("210005"),
    HTTP_INTERFACE_RESPONSE_HEADER_DUPLICATE_KEY("210006"),

    SPACE_NOT_FOUND("220001"),
    SUB_SPACE_NOT_FOUND("220002"),
    HTTP_INTERFACE_NOT_FOUND("220003"),
    HTTP_INTERFACE_BRANCH_SCRIPT_NOT_FOUND("220004"),

    UNEXPECTED_ERROR("900000");

    private final String code;

    @Override
    public String getCode() {
        return code;
    }

    ErrorCode(String code) {
        this.code = code;
    }
}
