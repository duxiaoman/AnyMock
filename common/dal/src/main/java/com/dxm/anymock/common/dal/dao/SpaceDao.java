package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.dal.dto.SpaceDTO;

import java.util.List;

public interface SpaceDao {
    List<SpaceDTO> tree();
    List<Space> selectByParentId(Long parentId);
    void insert(Space space);
    void update(Space space);
    void delete(Long id);
    Integer level(Long id);
}
