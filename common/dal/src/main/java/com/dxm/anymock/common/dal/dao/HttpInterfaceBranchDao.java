package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;

import java.util.List;

public interface HttpInterfaceBranchDao {
    void batchCreate(List<HttpInterfaceBranchBO> httpInterfaceBranchBOList, Long httpInterfaceId);
    void batchDelete(Long httpInterfaceId);
    List<HttpInterfaceBranchBO> batchQuery(Long httpInterfaceId);
}
