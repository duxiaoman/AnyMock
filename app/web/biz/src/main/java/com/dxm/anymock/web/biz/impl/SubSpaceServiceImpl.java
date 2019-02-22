package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.entity.SubSpace;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.dao.SubSpaceDao;
import com.dxm.anymock.web.biz.SubSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubSpaceServiceImpl implements SubSpaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private SubSpaceDao subSpaceDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Override
    public void insert(SubSpace subSpace) {
        if (!spaceDao.exists(subSpace.getSpaceId())) {
            throw new BaseException(ErrorCode.SPACE_NOT_FOUND);
        }
        subSpaceDao.insert(subSpace);
    }

    @Override
    public void update(SubSpace subSpace) {
        if (!spaceDao.exists(subSpace.getSpaceId())) {
            throw new BaseException(ErrorCode.SPACE_NOT_FOUND);
        }
        subSpaceDao.update(subSpace);
    }

    @Override
    public void delete(Long id) {
        httpInterfaceDao.selectBySubSpaceId(id).forEach(httpInterfaceDTO -> {
            httpInterfaceDao.delete(httpInterfaceDTO.getId());
        });
        subSpaceDao.delete(id);
    }
}
