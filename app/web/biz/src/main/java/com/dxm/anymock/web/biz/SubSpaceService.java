package com.dxm.anymock.web.biz;

import com.dxm.anymock.common.base.entity.SubSpace;

public interface SubSpaceService {
    void insert(SubSpace subSpace);
    void update(SubSpace subSpace);
    void delete(Long id);
}
