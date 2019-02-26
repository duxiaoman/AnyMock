package com.dxm.anymock.common.dal.dao.httpinterface;

import com.dxm.anymock.common.base.entity.HttpHeader;

import java.util.List;

public interface CallbackRequestHeaderDao {
    void insert(Long httpInterfaceId, List<HttpHeader> httpHeaders);
    void deleteByHttpInterfaceId(Long httpInterfaceId);
    List<HttpHeader> selectByHttpInterfaceId(Long httpInterfaceId);
}
