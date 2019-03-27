package com.dxm.anymock.manager.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.model.SpaceBO;
import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import com.dxm.anymock.manager.biz.model.response.dto.SpaceDTO;
import com.dxm.anymock.manager.biz.security.PrivilegeVerifier;
import com.dxm.anymock.manager.biz.security.SecurityContextHolder;
import com.dxm.anymock.manager.biz.security.UserRole;
import com.dxm.anymock.manager.biz.service.HttpInterfaceService;
import com.dxm.anymock.manager.biz.service.SpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static com.dxm.anymock.common.dal.DalConstants.ORDER_BY_GMT_MODIFIED_DESC;
import static com.dxm.anymock.common.dal.DalConstants.ROOT_SPACE_ID;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    private SpaceDTO convertToDTO(SpaceBO spaceBO, List<Long> parentPath) {
        SpaceDTO spaceDTO = new SpaceDTO();
        BeanUtils.copyProperties(spaceBO, spaceDTO);
        spaceDTO.setAllowCreateSpace(spaceBO.canCreateSpace());
        spaceDTO.setAllowCreateInterface(spaceBO.canCreateInterface());
        spaceDTO.setVariable(PrivilegeVerifier.hasPermission(spaceBO.getAccessAuthority()));

        List<Long> newPath = new LinkedList<>(parentPath);
        newPath.add(spaceBO.getId());
        spaceDTO.setPath(newPath);
        return spaceDTO;
    }

    private List<SpaceDTO> buildSpaceDTOTree(SpaceDTO parentSpaceDTO) {
        List<SpaceBO> spaceBOList
                = spaceDao.queryByParentIdOrderByClause(parentSpaceDTO.getId(), ORDER_BY_GMT_MODIFIED_DESC);
        List<SpaceDTO> spaceDTOList = new LinkedList<>();
        spaceBOList.forEach(spaceBO -> {
            SpaceDTO spaceDTO = convertToDTO(spaceBO, parentSpaceDTO.getPath());
            spaceDTO.setChildren(buildSpaceDTOTree(spaceDTO));
            spaceDTOList.add(spaceDTO);
        });
        return spaceDTOList;
    }

    @Override
    public List<SpaceDTO> tree() {
        SpaceDTO fakeRootSpaceDTO = new SpaceDTO();
        fakeRootSpaceDTO.setId(ROOT_SPACE_ID);
        fakeRootSpaceDTO.setPath(new LinkedList<>());
        return buildSpaceDTOTree(fakeRootSpaceDTO);
    }

    @Override
    public void create(SpaceBO spaceBO) {
        SpaceBO parentSpaceBO;
        if (spaceBO.getParentId().equals(ROOT_SPACE_ID)) {
            parentSpaceBO = new SpaceBO();
            parentSpaceBO.setId(ROOT_SPACE_ID);
            parentSpaceBO.setLevel(0);

            AccessAuthority accessAuthority;
            if (SecurityContextHolder.getUserRole() == UserRole.ADMIN) {
                accessAuthority = AccessAuthority.PROTECTED;
            } else {
                accessAuthority = AccessAuthority.PUBLIC;
            }
            parentSpaceBO.setAccessAuthority(accessAuthority);
        } else {
            parentSpaceBO = spaceDao.queryById(spaceBO.getParentId());
            if (parentSpaceBO == null) {
                throw new BizException(ResultCode.NOT_FOUND_PARENT_SPACE);
            }
        }

        if (!parentSpaceBO.canCreateSpace()) {
            throw new BizException(ResultCode.CAN_NOT_CREATE_SPACE_UNDER_THIS_LEVEL);
        }

        if (!PrivilegeVerifier.hasPermission(parentSpaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }

        spaceBO.setAccessAuthority(parentSpaceBO.getAccessAuthority());
        spaceBO.setLevel(parentSpaceBO.getLevel() + 1);

        spaceDao.create(spaceBO);
    }

    @Override
    public void update(SpaceBO spaceBO) {
        SpaceBO oldSpaceBO = spaceDao.queryById(spaceBO.getId());
        if (oldSpaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_SPACE);
        }

        if (!PrivilegeVerifier.hasPermission(oldSpaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }

        spaceBO.setParentId(null);
        spaceBO.setAccessAuthority(null);
        spaceBO.setLevel(null);

        spaceDao.update(spaceBO);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SpaceBO spaceBO = spaceDao.queryById(id);
        if (spaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_SPACE);
        }

        if (!PrivilegeVerifier.hasPermission(spaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }

        spaceDao.queryByParentId(id).forEach(space -> {
            Long spaceId = space.getId();
            httpInterfaceDao.queryBySpaceId(spaceId).forEach(httpInterfaceDTO -> {
                httpInterfaceService.delete(httpInterfaceDTO.getId());
            });
            delete(spaceId);
        });
        spaceDao.delete(id);
    }
}
