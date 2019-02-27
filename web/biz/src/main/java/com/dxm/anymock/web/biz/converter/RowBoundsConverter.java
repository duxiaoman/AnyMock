package com.dxm.anymock.web.biz.converter;

import com.dxm.anymock.web.biz.api.request.BasePagedRequest;
import org.apache.ibatis.session.RowBounds;

public class RowBoundsConverter {
    public static RowBounds convertFromPagedRequest(BasePagedRequest basePagedRequest) {
        int offset = basePagedRequest.getItemsPerPage() * (basePagedRequest.getPage() - 1);
        int limit = basePagedRequest.getItemsPerPage();
        return new RowBounds(offset, limit);
    }
}