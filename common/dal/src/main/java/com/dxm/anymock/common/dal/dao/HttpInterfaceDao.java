package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface HttpInterfaceDao {
    List<HttpInterface> selectAll(RowBounds rowBounds);
    Long countAll();
    List<HttpInterface> selectBySpaceId(Long spaceId, RowBounds rowBounds);
    Long countBySpaceId(Long spaceId);
    List<HttpInterface> selectBySpaceId(Long spaceId);
    HttpInterface selectById(Long id);
    HttpInterface selectByRequestType(RequestType requestType);
    Long countByUri(String uri);
    BranchScript selectBranchScript(Long id, String branchName);
    void insert(HttpInterface httpInterface);
    void update(HttpInterface httpInterface);
    void delete(Long id);

    void start(Long id);
    void shutdown(Long id);
}
