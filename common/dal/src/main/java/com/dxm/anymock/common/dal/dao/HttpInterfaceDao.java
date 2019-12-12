package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface HttpInterfaceDao {
    HttpInterfaceBO queryById(Long id);
    List<HttpInterfaceBO> queryAllWithRowBoundsOrderByClause(RowBounds rowBounds, String orderByClause);
    List<HttpInterfaceBO> queryBySpaceIdWithRowBoundsOrderByClause(Long spaceId, RowBounds rowBounds, String orderByClause);
    List<HttpInterfaceBO> queryBySpaceId(Long spaceId);
    HttpInterfaceBO queryByKey(HttpInterfaceKeyBO httpInterfaceKeyBO);
    Long create(HttpInterfaceBO httpInterfaceBO);
    void update(HttpInterfaceBO httpInterfaceBO);
    void delete(Long id);
    Long countAll();
    Long countBySpaceId(Long spaceId);
}
