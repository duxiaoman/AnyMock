package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.SpaceBO;

import java.util.List;

public interface SpaceDao {
    SpaceBO queryById(Long id);
    List<SpaceBO> queryByParentId(Long parentId);
    List<SpaceBO> queryByParentIdOrderByClause(Long parentId, String orderByClause);
    void create(SpaceBO spaceBO);
    void update(SpaceBO spaceBO);
    void delete(Long id);
}
