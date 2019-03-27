package com.dxm.anymock.manager.biz;

import com.dxm.anymock.manager.biz.model.request.BasePagingRequest;
import org.apache.ibatis.session.RowBounds;

public class RowBoundsConverter {
    public static RowBounds convert(BasePagingRequest basePagingRequest) {
        int offset = basePagingRequest.getItemsPerPage() * (basePagingRequest.getPage() - 1);
        int limit = basePagingRequest.getItemsPerPage();
        return new RowBounds(offset, limit);
    }
}
