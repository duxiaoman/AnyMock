package com.dxm.anymock.manager.biz.service;

import com.dxm.anymock.common.dal.model.SpaceBO;
import com.dxm.anymock.manager.biz.model.response.dto.SpaceDTO;

import java.util.List;

public interface SpaceService {
    List<SpaceDTO> tree();
    void create(SpaceBO spaceBO);
    void update(SpaceBO spaceBO);
    void delete(Long id);
}
