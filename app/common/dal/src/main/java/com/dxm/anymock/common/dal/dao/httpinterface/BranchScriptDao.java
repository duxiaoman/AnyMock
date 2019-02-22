package com.dxm.anymock.common.dal.dao.httpinterface;

import com.dxm.anymock.common.base.entity.BranchScript;

import java.util.List;

public interface BranchScriptDao {
    void insert(Long httpInterfaceId, List<BranchScript> httpHeaders);
    void deleteByHttpInterfaceId(Long httpInterfaceId);
    List<BranchScript> selectByHttpInterfaceId(Long httpInterfaceId);
    BranchScript selectByHttpInterfaceIdAndName(Long httpInterfaceId, String name);
}
