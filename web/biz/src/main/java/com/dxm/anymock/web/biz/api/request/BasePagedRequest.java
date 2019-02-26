package com.dxm.anymock.web.biz.api.request;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasePagedRequest {
    @NotNull
    @Min(0)
    private Integer page;
    
    @NotNull
    @Min(0)
    private Integer itemNumPerPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getItemNumPerPage() {
        return itemNumPerPage;
    }

    public void setItemNumPerPage(Integer itemNumPerPage) {
        this.itemNumPerPage = itemNumPerPage;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
