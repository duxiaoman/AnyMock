package com.dxm.anymock.manager.biz.model.response;

import com.dxm.anymock.manager.biz.model.response.dto.PagingDataDTO;

import java.util.Map;

public class PagingDataResponse<E> extends BaseResponse {
    private PagingDataDTO<E> data;

    public PagingDataResponse(Map<String, String> resultMap) {
        super(resultMap);
    }

    public PagingDataResponse(Map<String, String> resultMap, PagingDataDTO<E> data) {
        super(resultMap);
        this.data = data;
    }

    public PagingDataDTO<E> getData() {
        return data;
    }

    public void setData(PagingDataDTO<E> data) {
        this.data = data;
    }
}
