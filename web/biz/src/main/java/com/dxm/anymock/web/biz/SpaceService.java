package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.dal.dto.SpaceDTO;

import java.util.List;

public interface SpaceService {
    List<SpaceDTO> tree();
    void insert(Space space);
    void update(Space space);
    void delete(Long id);
}
