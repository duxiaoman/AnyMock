package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;

import java.util.List;

public interface HttpInterfaceDao {
    List<HttpInterface> selectBySubSpaceId(Long subSpaceId);
    HttpInterface selectById(Long id);
    HttpInterface selectByRequestType(RequestType requestType);
    BranchScript selectBranchScript(Long id, String branchName);
    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
