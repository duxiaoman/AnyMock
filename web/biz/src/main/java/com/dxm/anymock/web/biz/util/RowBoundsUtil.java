package com.dxm.anymock.web.biz.util;

import com.dxm.anymock.web.biz.api.request.BasePagedRequest;
import org.apache.ibatis.session.RowBounds;

public class RowBoundsUtil {
    public static RowBounds convertFromPagedRequest(BasePagedRequest basePagedRequest) {
        int offset = basePagedRequest.getItemsPerPage() * basePagedRequest.getPage();
        int limit = basePagedRequest.getItemsPerPage();
        return new RowBounds(offset, limit);
    }
}
