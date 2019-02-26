package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.web.biz.api.request.HttpInterfacePagedRequest;
import com.dxm.anymock.web.biz.api.request.HttpInterfaceSnapshotPagedRequest;
import com.dxm.anymock.web.biz.api.request.BasePagedRequest;
import com.dxm.anymock.web.biz.api.response.PagedData;

import java.util.List;

public interface HttpInterfaceService {
    PagedData selectAll(BasePagedRequest request);
    PagedData selectBySpaceId(HttpInterfacePagedRequest request);
    HttpInterface selectById(Long id);
    HttpInterface selectByRequestType(RequestType requestType);

    PagedData selectSnapshotByHttpInterfaceId(HttpInterfaceSnapshotPagedRequest request);
    HttpInterfaceSnapshot selectSnapshotById(Long id);

    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
