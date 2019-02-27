package com.dxm.anymock.web.biz.api.request;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasePagedRequest {
    @NotNull
    @Min(1)
    private Integer page;
    
    @NotNull
    @Min(0)
    private Integer itemsPerPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
