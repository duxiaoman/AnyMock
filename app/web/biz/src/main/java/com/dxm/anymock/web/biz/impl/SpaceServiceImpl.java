package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.dao.SubSpaceDao;
import com.dxm.anymock.web.biz.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private SubSpaceDao subSpaceDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Override
    public List<Space> list() {
        List<Space> spaceList = spaceDao.list();
        spaceList.forEach(spaceDTO ->
            spaceDTO.setSubSpaceList(subSpaceDao.selectBySpaceId(spaceDTO.getId()))
        );
        return spaceList;
    }

    @Override
    public void insert(Space space) {
        spaceDao.insert(space);
    }

    @Override
    public void update(Space space) {
        spaceDao.update(space);
    }

    @Override
    public void delete(Long id) {
        subSpaceDao.selectBySpaceId(id).forEach(subSpaceDTO -> {
            Long subSpaceId = subSpaceDTO.getId();
            httpInterfaceDao.selectBySubSpaceId(subSpaceId).forEach(httpInterfaceDTO -> {
                httpInterfaceDao.delete(httpInterfaceDTO.getId());
            });
            subSpaceDao.delete(subSpaceId);
        });
        spaceDao.delete(id);
    }
}
