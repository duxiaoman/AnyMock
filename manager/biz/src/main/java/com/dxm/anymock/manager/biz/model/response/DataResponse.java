package com.dxm.anymock.manager.biz.model.response;

import java.util.Map;

public class DataResponse<E> extends BaseResponse {
    private E data;

    public DataResponse(Map<String, String> resultMap) {
        super(resultMap);
    }

    public DataResponse(Map<String, String> resultMap, E data) {
        super(resultMap);
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
