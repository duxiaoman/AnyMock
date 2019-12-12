package com.dxm.anymock.manager.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.HttpInterfaceCacheManager;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import com.dxm.anymock.common.dal.model.SpaceBO;
import com.dxm.anymock.common.dal.model.enums.OpType;
import com.dxm.anymock.manager.biz.RowBoundsConverter;
import com.dxm.anymock.manager.biz.model.request.HttpInterfaceConflictDetectionRequest;
import com.dxm.anymock.manager.biz.model.request.SpaceIdRequest;
import com.dxm.anymock.manager.biz.model.request.CriteriaPagingRequest;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.CoreHostInfoDTO;
import com.dxm.anymock.manager.biz.model.response.dto.HttpInterfaceDTO;
import com.dxm.anymock.manager.biz.model.response.dto.PagingDataDTO;
import com.dxm.anymock.manager.biz.model.request.BasePagingRequest;
import com.dxm.anymock.manager.biz.security.PrivilegeVerifier;
import com.dxm.anymock.manager.biz.security.SecurityContextHolder;
import com.dxm.anymock.manager.biz.service.HostInfoService;
import com.dxm.anymock.manager.biz.service.HttpInterfaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static com.dxm.anymock.common.dal.DalConstants.ORDER_BY_GMT_MODIFIED_DESC;
import static com.dxm.anymock.common.dal.DalConstants.ROOT_SPACE_ID;

@Service
public class HttpInterfaceServiceImpl implements HttpInterfaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Autowired
    private HttpInterfaceCacheManager httpInterfaceCacheManager;

    @Autowired
    private HttpInterfaceSnapshotDao httpInterfaceSnapshotDao;

    @Override
    public HttpInterfaceDTO queryById(Long id) {
        HttpInterfaceBO httpInterfaceBO = httpInterfaceDao.queryById(id);
        if (httpInterfaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_HTTP_INTERFACE);
        }
        return convertToDTO(httpInterfaceBO);
    }

    @Override
    public HttpInterfaceDTO create(HttpInterfaceBO httpInterfaceBO) {
        SpaceBO spaceBO = spaceDao.queryById(httpInterfaceBO.getSpaceId());
        checkSpaceVariable(spaceBO);
        httpInterfaceBO.setAccessAuthority(spaceBO.getAccessAuthority());
        httpInterfaceBO.setLastUpdateUser(SecurityContextHolder.getUsername());

        Long id = httpInterfaceDao.create(httpInterfaceBO);
        return convertToDTO(httpInterfaceDao.queryById(id));
    }

    @Override
    public void update(HttpInterfaceBO httpInterfaceBO) {
        HttpInterfaceBO oldHttpInterfaceBO = httpInterfaceDao.queryById(httpInterfaceBO.getId());
        checkHttpInterfaceVariable(oldHttpInterfaceBO);

        SpaceBO spaceBO = spaceDao.queryById(httpInterfaceBO.getSpaceId());
        checkSpaceVariable(spaceBO);

        httpInterfaceBO.setAccessAuthority(spaceBO.getAccessAuthority());
        httpInterfaceBO.setLastUpdateUser(SecurityContextHolder.getUsername());

        httpInterfaceCacheManager.clear(oldHttpInterfaceBO);
        httpInterfaceSnapshotDao.create(oldHttpInterfaceBO, OpType.UPDATE);
        httpInterfaceDao.update(httpInterfaceBO);
    }

    @Override
    public void delete(Long id) {
        HttpInterfaceBO oldHttpInterfaceBO = httpInterfaceDao.queryById(id);
        checkHttpInterfaceVariable(oldHttpInterfaceBO);
        httpInterfaceCacheManager.clear(oldHttpInterfaceBO);
        httpInterfaceSnapshotDao.create(oldHttpInterfaceBO, OpType.DELETE);
        httpInterfaceDao.delete(id);
    }

    @Override
    public ConflictJudgementDTO conflictDetection(HttpInterfaceConflictDetectionRequest request) {
        ConflictJudgementDTO conflictJudgementDTO = new ConflictJudgementDTO();
        conflictJudgementDTO.setDetectable(true);

        HttpInterfaceKeyBO httpInterfaceKeyBO = new HttpInterfaceKeyBO();
        httpInterfaceKeyBO.setRequestUri(request.getUri());
        httpInterfaceKeyBO.setRequestMethod(request.getMethod());
        HttpInterfaceBO httpInterfaceBO = httpInterfaceDao.queryByKey(httpInterfaceKeyBO);
        if (httpInterfaceBO == null || httpInterfaceBO.getId().equals(request.getId())) {
            conflictJudgementDTO.setConflict(false);
        } else {
            conflictJudgementDTO.setConflict(true);
        }
        return conflictJudgementDTO;
    }

    @Override
    public PagingDataDTO<HttpInterfaceDTO> queryAll(BasePagingRequest request) {
        PagingDataDTO<HttpInterfaceDTO> pagingDataDTO = new PagingDataDTO<>(request);
        pagingDataDTO.setList(convertToDTOList(httpInterfaceDao.queryAllWithRowBoundsOrderByClause(
                        RowBoundsConverter.convert(request), ORDER_BY_GMT_MODIFIED_DESC)));
        pagingDataDTO.setTotal(httpInterfaceDao.countAll());
        return pagingDataDTO;
    }

    @Override
    public PagingDataDTO<HttpInterfaceDTO> queryBySpaceId(CriteriaPagingRequest<SpaceIdRequest> request) {
        Long spaceId = request.getCriteria().getSpaceId();
        PagingDataDTO<HttpInterfaceDTO> pagingDataDTO = new PagingDataDTO<>(request);
        pagingDataDTO.setList(convertToDTOList(httpInterfaceDao.queryBySpaceIdWithRowBoundsOrderByClause(
                spaceId, RowBoundsConverter.convert(request), ORDER_BY_GMT_MODIFIED_DESC)));
        pagingDataDTO.setTotal(httpInterfaceDao.countBySpaceId(spaceId));
        return pagingDataDTO;
    }

    private List<HttpInterfaceDTO> convertToDTOList(List<HttpInterfaceBO> httpInterfaceBOList) {
        List<HttpInterfaceDTO> httpInterfaceDTOList = new LinkedList<>();
        httpInterfaceBOList.forEach(httpInterfaceBO
                -> httpInterfaceDTOList.add(convertToDTO(httpInterfaceBO)));
        return httpInterfaceDTOList;
    }

    private HttpInterfaceDTO convertToDTO(HttpInterfaceBO httpInterfaceBO) {
        HttpInterfaceDTO httpInterfaceDTO = new HttpInterfaceDTO();
        BeanUtils.copyProperties(httpInterfaceBO, httpInterfaceDTO);

        // path
        Long spaceId = httpInterfaceBO.getSpaceId();
        LinkedList<Long> path = new LinkedList<>();
        while (!spaceId.equals(ROOT_SPACE_ID)) {
            path.addFirst(spaceId);
            spaceId = spaceDao.queryById(spaceId).getParentId();
        }
        httpInterfaceDTO.setPath(path);

        // variable
        httpInterfaceDTO.setVariable(PrivilegeVerifier.hasPermission(httpInterfaceBO.getAccessAuthority()));
        return httpInterfaceDTO;
    }
    private void checkHttpInterfaceVariable(HttpInterfaceBO oldHttpInterfaceBO) {
        if (oldHttpInterfaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_HTTP_INTERFACE);
        }
        if (!PrivilegeVerifier.hasPermission(oldHttpInterfaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }
    }

    private void checkSpaceVariable(SpaceBO spaceBO) {
        if (spaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_PARENT_SPACE);
        }
        if (!spaceBO.canCreateInterface()) {
            throw new BizException(ResultCode.CAN_NOT_CREATE_INTERFACE_UNDER_THIS_LEVEL);
        }
        if (!PrivilegeVerifier.hasPermission(spaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }
    }
}
