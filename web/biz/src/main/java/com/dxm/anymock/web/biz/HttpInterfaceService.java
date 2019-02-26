package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;

import java.util.List;

public interface HttpInterfaceService {
    List<HttpInterface> selectAll();
    List<HttpInterface> selectBySpaceId(Long spaceId);
    HttpInterface selectById(Long id);
    HttpInterface selectByRequestType(RequestType requestType);

    List<HttpInterfaceSnapshot> selectSnapshotByHttpInterfaceId(Long httpInterfaceId);
    HttpInterfaceSnapshot selectSnapshotById(Long id);

    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
