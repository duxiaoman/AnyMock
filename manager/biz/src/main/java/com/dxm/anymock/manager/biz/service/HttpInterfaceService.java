package com.dxm.anymock.manager.biz.service;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.manager.biz.model.request.HttpInterfaceConflictDetectionRequest;
import com.dxm.anymock.manager.biz.model.request.SpaceIdRequest;
import com.dxm.anymock.manager.biz.model.request.CriteriaPagingRequest;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.HttpInterfaceDTO;
import com.dxm.anymock.manager.biz.model.response.dto.PagingDataDTO;
import com.dxm.anymock.manager.biz.model.request.BasePagingRequest;

public interface HttpInterfaceService {
    HttpInterfaceDTO queryById(Long id);
    PagingDataDTO<HttpInterfaceDTO> queryAll(BasePagingRequest request);
    PagingDataDTO<HttpInterfaceDTO> queryBySpaceId(CriteriaPagingRequest<SpaceIdRequest> request);
    HttpInterfaceDTO create(HttpInterfaceBO httpInterfaceBO);
    void update(HttpInterfaceBO httpInterfaceBO);
    void delete(Long id);
    ConflictJudgementDTO conflictDetection(HttpInterfaceConflictDetectionRequest request);
}
