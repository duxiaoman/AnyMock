package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.web.biz.api.response.SpaceTreeNode;
import com.dxm.anymock.web.biz.HttpInterfaceService;
import com.dxm.anymock.web.biz.SpaceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    private SpaceTreeNode fillingSpaceTreeNode(Space space, List<Long> path) {
        SpaceTreeNode spaceTreeNode = new SpaceTreeNode();
        BeanUtils.copyProperties(space, spaceTreeNode);
        path.add(space.getId());
        spaceTreeNode.setPath(path);
        if (path.size() == GlobalConstant.ALLOW_CREATE_INTERFACE_SPACE_LEVEL) {
            spaceTreeNode.setAllowCreateInterface(true);
        } else {
            spaceTreeNode.setAllowCreateInterface(false);
        }

        List<SpaceTreeNode> children = new LinkedList<>();
        List<Space> spaceList = spaceDao.selectByParentId(space.getId());
        spaceList.forEach(localSpace -> children.add(fillingSpaceTreeNode(localSpace, new LinkedList<>(path))));
        spaceTreeNode.setChildren(children);
        return spaceTreeNode;
    }

    @Override
    public List<SpaceTreeNode> tree() {
        List<SpaceTreeNode> spaceTreeNodeList = new LinkedList<>();
        List<Space> spaceList = spaceDao.selectByParentId(0L);
        spaceList.forEach(space -> spaceTreeNodeList.add(fillingSpaceTreeNode(space, new LinkedList<>())));
        return spaceTreeNodeList;
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
            httpInterfaceDao.selectBySpaceId(spaceId).forEach(httpInterfaceDTO -> {
                httpInterfaceService.delete(httpInterfaceDTO.getId());
            });
            delete(spaceId);
        });
        spaceDao.delete(id);
    }
}
