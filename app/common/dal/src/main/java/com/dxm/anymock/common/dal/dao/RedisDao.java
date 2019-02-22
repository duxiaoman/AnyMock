package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;

public interface RedisDao {
    void clearCache(RequestType requestType);
    HttpInterface getHttpInterface(RequestType requestType);
    void setHttpInterface(RequestType requestType, HttpInterface httpInterface);
}
