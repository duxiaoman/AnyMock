package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.Space;

import java.util.List;

public interface SpaceService {
    List<Space> list();
    void insert(Space space);
    void update(Space space);
    void delete(Long id);
}
