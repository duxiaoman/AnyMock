/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.manager.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.InterfaceCacheManager;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.dao.TcpInterfaceDao;
import com.dxm.anymock.common.dal.dao.TcpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.entity.TcpInterfaceDO;
import com.dxm.anymock.common.dal.model.*;
import com.dxm.anymock.common.dal.model.enums.OpType;
import com.dxm.anymock.manager.biz.RowBoundsConverter;
import com.dxm.anymock.manager.biz.model.request.BasePagingRequest;
import com.dxm.anymock.manager.biz.model.request.CriteriaPagingRequest;
import com.dxm.anymock.manager.biz.model.request.SpaceIdRequest;
import com.dxm.anymock.manager.biz.model.request.TcpInterfaceConflictDetectionRequest;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.HttpInterfaceDTO;
import com.dxm.anymock.manager.biz.model.response.dto.PagingDataDTO;
import com.dxm.anymock.manager.biz.model.response.dto.TcpInterfaceDTO;
import com.dxm.anymock.manager.biz.security.PrivilegeVerifier;
import com.dxm.anymock.manager.biz.security.SecurityContextHolder;
import com.dxm.anymock.manager.biz.service.SpaceService;
import com.dxm.anymock.manager.biz.service.TcpInterfaceService;
import com.dxm.anymock.manager.integration.client.impl.TcpServiceClientImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.dxm.anymock.common.dal.DalConstants.ORDER_BY_GMT_MODIFIED_DESC;
import static com.dxm.anymock.common.dal.DalConstants.ROOT_SPACE_ID;

/**
 * Created by jff on 2019/8/26.
 */

@Service
public class TcpInterfaceServiceImpl implements TcpInterfaceService {

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private TcpInterfaceDao tcpInterfaceDao;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private InterfaceCacheManager interfaceCacheManager;

    @Autowired
    private TcpInterfaceSnapshotDao tcpInterfaceSnapshotDao;

    @Autowired
    private TcpServiceClientImpl tcpServiceClient;

    private static final Logger logger = LoggerFactory.getLogger(TcpInterfaceServiceImpl.class);


    @Override
    public TcpInterfaceDTO create(TcpInterfaceBO tcpInterfaceBO) {
        SpaceBO spaceBO = spaceDao.queryById(tcpInterfaceBO.getSpaceId());

        spaceService.checkSpaceVariable(spaceBO);
        tcpInterfaceBO.setAccessAuthority(spaceBO.getAccessAuthority());
        tcpInterfaceBO.setLastUpdateUser(SecurityContextHolder.getUsername());

        // 更新db
        Long id = tcpInterfaceDao.create(tcpInterfaceBO);
        logger.info("create tcp interface suc, interface id: {}", id);

        TcpInterfaceBO tbNew = tcpInterfaceDao.queryById(id);

        // 启动tcp server
        if (tbNew.getStart()) {
            tcpServiceClient.startTcpService(tbNew.getRequestUri());
        }

        return convertToDTO(tbNew);
    }


    @Override
    public TcpInterfaceDTO queryById(Long id) {
        TcpInterfaceBO tcpInterfaceBO = tcpInterfaceDao.queryById(id);
        if (tcpInterfaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_TCP_INTERFACE);
        }
        return convertToDTO(tcpInterfaceBO);
    }


    @Override
    public PagingDataDTO<TcpInterfaceDTO> queryBySpaceId(CriteriaPagingRequest<SpaceIdRequest> request) {
        Long spaceId = request.getCriteria().getSpaceId();
        PagingDataDTO<TcpInterfaceDTO> pagingDataDTO = new PagingDataDTO<>(request);
        pagingDataDTO.setList(convertToDTOList(tcpInterfaceDao.queryBySpaceIdWithRowBoundsOrderByClause(
                spaceId, RowBoundsConverter.convert(request), ORDER_BY_GMT_MODIFIED_DESC)));
        pagingDataDTO.setTotal(tcpInterfaceDao.countBySpaceId(spaceId));
        return pagingDataDTO;
    }


    @Override
    public PagingDataDTO<TcpInterfaceDTO> queryAll(BasePagingRequest request) {
        PagingDataDTO<TcpInterfaceDTO> pagingDataDTO = new PagingDataDTO<>(request);
        pagingDataDTO.setList(convertToDTOList(tcpInterfaceDao.queryAllWithRowBoundsOrderByClause(
                RowBoundsConverter.convert(request), ORDER_BY_GMT_MODIFIED_DESC)));
        pagingDataDTO.setTotal(tcpInterfaceDao.countAll());
        return pagingDataDTO;
    }

    @Override
    public void changeTcpServerStatus(Long id, Boolean isStart) {

        logger.info(
                "变更tcp服务状态,收到入参interfaceId=" + id + ",isStart=" + isStart);

        TcpInterfaceBO tcpInterfaceBO = tcpInterfaceDao.queryById(id);
        // 检查是否存在,是否有修改权限
        checkTcpInterfaceVariable(tcpInterfaceBO);

        // clear redis
        interfaceCacheManager.clear(tcpInterfaceBO);

        // 更新db
        tcpInterfaceDao.updateStatus(tcpInterfaceBO, isStart);

        // change tcp port
        tcpServiceClient.changeTcpServerStatus(tcpInterfaceBO.getRequestUri(), isStart);
        tcpInterfaceSnapshotDao.create(tcpInterfaceBO, OpType.UPDATE);
    }

    @Override
    public void update(TcpInterfaceBO tcpInterfaceBO) {
        TcpInterfaceBO oldTcpInterfaceBO = tcpInterfaceDao.queryById(tcpInterfaceBO.getId());
        checkTcpInterfaceVariable(oldTcpInterfaceBO);

        SpaceBO spaceBO = spaceDao.queryById(tcpInterfaceBO.getSpaceId());
        spaceService.checkSpaceVariable(spaceBO);

        tcpInterfaceBO.setAccessAuthority(spaceBO.getAccessAuthority());
        tcpInterfaceBO.setLastUpdateUser(SecurityContextHolder.getUsername());

        interfaceCacheManager.clear(oldTcpInterfaceBO);
        tcpInterfaceDao.update(tcpInterfaceBO);
        tcpServiceClient.updateTcpService(oldTcpInterfaceBO.getRequestUri(), tcpInterfaceBO.getRequestUri());
        tcpInterfaceSnapshotDao.create(oldTcpInterfaceBO, OpType.UPDATE);
    }


    @Override
    public void delete(Long id) {
        TcpInterfaceBO tcpInterfaceBO = tcpInterfaceDao.queryById(id);
        checkTcpInterfaceVariable(tcpInterfaceBO);
        tcpServiceClient.deleteTcpService(tcpInterfaceBO.getRequestUri());
        interfaceCacheManager.clear(tcpInterfaceBO);
        tcpInterfaceSnapshotDao.create(tcpInterfaceBO, OpType.DELETE);
        tcpInterfaceDao.delete(id);
    }


    @Override
    public ConflictJudgementDTO conflictDetection(TcpInterfaceConflictDetectionRequest request) {
        ConflictJudgementDTO conflictJudgementDTO = new ConflictJudgementDTO();
        conflictJudgementDTO.setDetectable(true);

        TcpInterfaceKeyBO tcpInterfaceKeyBO = new TcpInterfaceKeyBO();
        tcpInterfaceKeyBO.setRequestUri(request.getUri());
        TcpInterfaceBO tcpInterfaceBO = tcpInterfaceDao.queryByKey(tcpInterfaceKeyBO);

        if (tcpInterfaceBO == null || tcpInterfaceBO.getId().equals(request.getId())) {
            conflictJudgementDTO.setConflict(false);
        } else {
            conflictJudgementDTO.setConflict(true);
        }

        return conflictJudgementDTO;
    }

    private TcpInterfaceDTO convertToDTO(TcpInterfaceBO tcpInterfaceBO) {
        TcpInterfaceDTO tcpInterfaceDTO = new TcpInterfaceDTO();
        BeanUtils.copyProperties(tcpInterfaceBO, tcpInterfaceDTO);

        // path
        Long spaceId = tcpInterfaceDTO.getSpaceId();
        LinkedList<Long> path = new LinkedList<>();
        while (!spaceId.equals(ROOT_SPACE_ID)) {
            path.addFirst(spaceId);
            spaceId = spaceDao.queryById(spaceId).getParentId();
        }
        tcpInterfaceDTO.setPath(path);
        tcpInterfaceDTO.setVariable(PrivilegeVerifier.hasPermission(tcpInterfaceBO.getAccessAuthority()));
        return tcpInterfaceDTO;

    }

    private List<TcpInterfaceDTO> convertToDTOList(List<TcpInterfaceBO> tcpInterfaceBOList) {
        List<TcpInterfaceDTO> tcpInterfaceDTOList = new LinkedList<>();
        tcpInterfaceBOList.forEach(tcpInterfaceBO
                -> tcpInterfaceDTOList.add(convertToDTO(tcpInterfaceBO)));
        return tcpInterfaceDTOList;
    }


    private void checkTcpInterfaceVariable(TcpInterfaceBO oldTcpInterfaceBO) {
        if (oldTcpInterfaceBO == null) {
            throw new BizException(ResultCode.NOT_FOUND_TCP_INTERFACE);
        }
        if (!PrivilegeVerifier.hasPermission(oldTcpInterfaceBO.getAccessAuthority())) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        }
    }
}
