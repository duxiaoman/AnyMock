package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;

public interface RedisDao {
    void clearHttpInterfaceCache(RequestType requestType);
    HttpInterface getHttpInterface(RequestType requestType);
    void setHttpInterface(RequestType requestType, HttpInterface httpInterface);

    BranchScript getBranchScript(RequestType requestType, String branchName);
    void setBranchScript(RequestType requestType, String branchName, BranchScript branchScript);
}
