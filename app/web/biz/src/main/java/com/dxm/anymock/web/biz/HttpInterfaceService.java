package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;

import java.util.List;

public interface HttpInterfaceService {
    List<HttpInterface> selectBySubSpaceId(Long spaceId);
    HttpInterface selectById(Long id);
    HttpInterface selectByRequestType(RequestType requestType);

    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
