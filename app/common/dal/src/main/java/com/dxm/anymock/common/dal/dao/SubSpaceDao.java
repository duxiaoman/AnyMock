package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.SubSpace;

import java.util.List;

public interface SubSpaceDao {
    List<SubSpace> selectBySpaceId(Long spaceId);
    void insert(SubSpace subSpace);
    void update(SubSpace subSpace);
    void delete(Long id);
    boolean exists(Long id);
}
