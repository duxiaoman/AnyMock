package com.dxm.anymock.web.biz.api.response;

public class ConflictJudgement {
    private Boolean conflict;
    private Boolean mayConflict;

    public Boolean getConflict() {
        return conflict;
    }

    public void setConflict(Boolean conflict) {
        this.conflict = conflict;
    }

    public Boolean getMayConflict() {
        return mayConflict;
    }

    public void setMayConflict(Boolean mayConflict) {
        this.mayConflict = mayConflict;
    }
}
