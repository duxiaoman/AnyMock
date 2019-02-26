package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.web.biz.api.response.SpaceTreeNode;

import java.util.List;

public interface SpaceService {
    List<SpaceTreeNode> tree();
    void insert(Space space);
    void update(Space space);
    void delete(Long id);
}
