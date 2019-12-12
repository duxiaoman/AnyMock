package com.dxm.anymock.manager.biz.service;

import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.manager.biz.model.request.BasePagingRequest;
import com.dxm.anymock.manager.biz.model.request.CriteriaPagingRequest;
import com.dxm.anymock.manager.biz.model.request.SpaceIdRequest;
import com.dxm.anymock.manager.biz.model.request.TcpInterfaceConflictDetectionRequest;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.PagingDataDTO;
import com.dxm.anymock.manager.biz.model.response.dto.TcpInterfaceDTO;

import java.util.List;

/**
 * Created by jff on 2019/8/26.
 */
public interface TcpInterfaceService {

    /**
     * 新建tcp 接口
     *
     * @param tcpInterfaceBO
     */
    TcpInterfaceDTO create(TcpInterfaceBO tcpInterfaceBO);

    /**
     * 修改tcp接口状态 开启/关闭
     *
     * @param id
     * @param isStart
     * @return
     */
    void changeTcpServerStatus(Long id, Boolean isStart);


    /**
     * 更新tcp接口
     *
     * @param tcpInterfaceBO
     */
    void update(TcpInterfaceBO tcpInterfaceBO);


    /**
     * 删除tcp接口
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 冲突检查
     *
     * @param request
     * @return
     */
    ConflictJudgementDTO conflictDetection(TcpInterfaceConflictDetectionRequest request);


    TcpInterfaceDTO queryById(Long id);

    PagingDataDTO<TcpInterfaceDTO> queryBySpaceId(CriteriaPagingRequest<SpaceIdRequest> request);

    PagingDataDTO<TcpInterfaceDTO> queryAll(BasePagingRequest request);
}
