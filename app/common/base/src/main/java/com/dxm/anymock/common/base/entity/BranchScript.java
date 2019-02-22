package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.ValueCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BranchScript implements Serializable {

    @NotBlank(groups = {ValueCheck.class})
    private String name;

    @NotNull(groups = {ValueCheck.class})
    private String syncScript;

    @NotNull(groups = {ValueCheck.class})
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
