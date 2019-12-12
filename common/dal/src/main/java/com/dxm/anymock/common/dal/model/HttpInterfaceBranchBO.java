package com.dxm.anymock.common.dal.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HttpInterfaceBranchBO {
    @NotBlank
    private String name;

    @NotNull
    private String syncScript;

    @NotNull
    private String asyncScript;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyncScript() {
        return syncScript;
    }

    public void setSyncScript(String syncScript) {
        this.syncScript = syncScript;
    }

    public String getAsyncScript() {
        return asyncScript;
    }

    public void setAsyncScript(String asyncScript) {
        this.asyncScript = asyncScript;
    }
}
