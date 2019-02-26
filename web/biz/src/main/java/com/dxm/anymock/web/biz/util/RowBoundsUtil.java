package com.dxm.anymock.web.biz.util;

import com.dxm.anymock.web.biz.api.request.BasePagedRequest;
import org.apache.ibatis.session.RowBounds;

public class RowBoundsUtil {
    public static RowBounds convertFromPagedRequest(BasePagedRequest basePagedRequest) {
        int offset = basePagedRequest.getItemNumPerPage() * basePagedRequest.getPage();
        int limit = basePagedRequest.getItemNumPerPage();
        return new RowBounds(offset, limit);
    }
}
