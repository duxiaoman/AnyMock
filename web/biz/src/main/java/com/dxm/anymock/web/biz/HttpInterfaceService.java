package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.web.biz.api.request.HttpInterfaceConflictDetectionRequest;
import com.dxm.anymock.web.biz.api.request.HttpInterfacePagedRequest;
import com.dxm.anymock.web.biz.api.request.HttpInterfaceSnapshotPagedRequest;
import com.dxm.anymock.web.biz.api.request.BasePagedRequest;
import com.dxm.anymock.web.biz.api.response.ConflictJudgement;
import com.dxm.anymock.web.biz.api.response.HttpInterfaceDetail;
import com.dxm.anymock.web.biz.api.response.PagedData;

public interface HttpInterfaceService {

    PagedData selectAll(BasePagedRequest request);
    PagedData selectBySpaceId(HttpInterfacePagedRequest request);

    HttpInterfaceDetail selectById(Long id);

    ConflictJudgement conflictDetection(HttpInterfaceConflictDetectionRequest request);

    // not public
    HttpInterface selectByRequestType(RequestType requestType);

    // snapshot
    PagedData selectSnapshotByHttpInterfaceId(HttpInterfaceSnapshotPagedRequest request);
    HttpInterfaceSnapshot selectSnapshotById(Long id);

    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
