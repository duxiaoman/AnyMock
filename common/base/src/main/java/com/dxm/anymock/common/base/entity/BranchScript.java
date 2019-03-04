package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.CommonInsertCheck;
import com.dxm.anymock.common.base.check.CommonUpdateCheck;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BranchScript)) {
            return false;
        }
        BranchScript branchScript = (BranchScript)o;
        return new EqualsBuilder()
                .append(name, branchScript.name)
                .append(syncScript, branchScript.syncScript)
                .append(asyncScript, branchScript.asyncScript)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(syncScript)
                .append(asyncScript)
                .toHashCode();
    }

    private static Map<String, BranchScript> list2Map(List<BranchScript> branchScriptList) {
        Map<String, BranchScript> map = new HashMap<>();
        branchScriptList.forEach(branchScript -> map.put(branchScript.getName(), branchScript));
        return map;
    }

    public static boolean equals(List<BranchScript> left, List<BranchScript> right) {
        return list2Map(left).equals(list2Map(right));
    }
}