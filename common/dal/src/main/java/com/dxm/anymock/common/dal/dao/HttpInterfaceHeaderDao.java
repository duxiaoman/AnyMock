package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.HttpInterfaceHeaderBO;
import com.dxm.anymock.common.dal.model.enums.HttpHeaderType;

import java.util.List;

public interface HttpInterfaceHeaderDao {
    void batchCreate(List<HttpInterfaceHeaderBO> httpInterfaceHeaderBOList, Long httpInterfaceId, HttpHeaderType type);
    void batchDelete(Long httpInterfaceId, HttpHeaderType type);
    List<HttpInterfaceHeaderBO> batchQuery(Long httpInterfaceId, HttpHeaderType type);
}
