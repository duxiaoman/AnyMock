package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.dto.SpaceDTO;
import com.dxm.anymock.web.biz.HttpInterfaceService;
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
    private HttpInterfaceService httpInterfaceService;

    @Override
    public List<SpaceDTO> tree() {
        return spaceDao.tree();
    }

    @Override
    public void insert(Space space) {
        if (spaceDao.level(space.getParentId()) > (GlobalConstant.MAX_SPACE_LEVEL - 1)) {
            throw new BaseException(ErrorCode.SPACE_LEVEL_TOO_DEEP);
        }
        spaceDao.insert(space);
    }

    @Override
    public void update(Space space) {
        spaceDao.update(space);
    }

    @Override
    public void delete(Long id) {
        spaceDao.selectByParentId(id).forEach(space -> {
            Long spaceId = space.getId();
            httpInterfaceService.selectBySpaceId(spaceId).forEach(httpInterfaceDTO -> {
                httpInterfaceService.delete(httpInterfaceDTO.getId());
            });
            delete(spaceId);
        });
        spaceDao.delete(id);
    }
}
