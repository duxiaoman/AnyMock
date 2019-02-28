package com.dxm.anymock.web.biz.api.response;

import com.alibaba.fastjson.JSONObject;

public class ConflictJudgement {
    private Boolean conflict;
    private Boolean detectable;

    public Boolean getConflict() {
        return conflict;
    }

    public void setConflict(Boolean conflict) {
        this.conflict = conflict;
    }

    public Boolean getDetectable() {
        return detectable;
    }

    public void setDetectable(Boolean detectable) {
        this.detectable = detectable;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
