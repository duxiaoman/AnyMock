package com.dxm.anymock.web.biz.api.response;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.web.biz.api.request.BasePagedRequest;

public class PagedData {
    private Integer page;
    private Integer itemsPerPage;
    private Long total;
    private Object list;

    public PagedData(BasePagedRequest request) {
        this.page = request.getPage();
        this.itemsPerPage = request.getItemNumPerPage();
    }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
