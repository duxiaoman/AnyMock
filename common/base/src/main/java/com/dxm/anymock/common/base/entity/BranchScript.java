package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.CommonInsertCheck;
import com.dxm.anymock.common.base.check.CommonUpdateCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BranchScript implements Serializable {

    @NotBlank(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String name;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String syncScript;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
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
