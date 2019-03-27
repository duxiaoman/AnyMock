package com.dxm.anymock.manager.biz.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CriteriaPagingRequest<E> extends BasePagingRequest {
    @NotNull
    @Valid
    private E criteria;

    public E getCriteria() {
        return criteria;
    }

    public void setCriteria(E criteria) {
        this.criteria = criteria;
    }
}
