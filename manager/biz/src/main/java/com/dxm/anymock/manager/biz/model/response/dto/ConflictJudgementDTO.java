package com.dxm.anymock.manager.biz.model.response.dto;

public class ConflictJudgementDTO {
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
}
