package com.dxm.anymock.common.dal.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HttpInterfaceHeaderBO {
    @NotBlank
    private String name;

    @NotNull
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
