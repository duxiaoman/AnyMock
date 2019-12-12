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


package com.dxm.anymock.manager.web.controller;

import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.model.response.BaseResponse;
import com.dxm.anymock.common.base.model.response.DataResponse;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.manager.biz.model.request.*;

import com.dxm.anymock.manager.biz.model.response.PagingDataResponse;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.TcpInterfaceDTO;
import com.dxm.anymock.manager.biz.service.TcpInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.dxm.anymock.manager.web.WebConstants.URL_PREFIX_API_V2;

/**
 * Created by jff on 2019/8/26.
 */

@Controller
@RequestMapping(URL_PREFIX_API_V2)
public class TcpInterfaceController {

    @Autowired
    private TcpInterfaceService tcpInterfaceService;

    @Autowired
    private ResultCodeTranslator translator;


    @PostMapping("/interface_tcp/conflictDetection")
    @ResponseBody
    public DataResponse<ConflictJudgementDTO> conflictDetection(
            @Validated @RequestBody TcpInterfaceConflictDetectionRequest request
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                tcpInterfaceService.conflictDetection(request));
    }

    @PostMapping("/interface_tcp/selectById")
    @ResponseBody
    public DataResponse<TcpInterfaceDTO> queryById(
            @Validated @RequestBody IdRequest idRequest
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                tcpInterfaceService.queryById(idRequest.getId()));
    }


    @PostMapping("/interface_tcp/selectBySpaceId")
    @ResponseBody
    public PagingDataResponse<TcpInterfaceDTO> queryBySpaceId(
            @Validated @RequestBody CriteriaPagingRequest<SpaceIdRequest> request
    ) {
        return new PagingDataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                tcpInterfaceService.queryBySpaceId(request));
    }

    @PostMapping("/interface_tcp/selectAll")
    @ResponseBody
    public PagingDataResponse<TcpInterfaceDTO> queryAll(@Validated @RequestBody BasePagingRequest request) {
        return new PagingDataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                tcpInterfaceService.queryAll(request));
    }

    @PostMapping("/interface_tcp/insert")
    @ResponseBody
    public DataResponse<TcpInterfaceDTO> create(
            @Validated(value = {TcpInterfaceBO.Insert.class}) @RequestBody TcpInterfaceBO tcpInterfaceBO
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS_CREATE_TCP_INTERFACE),
                tcpInterfaceService.create(tcpInterfaceBO));
    }

    @PostMapping("/interface_tcp/changeTcpServerStatus")
    @ResponseBody
    public BaseResponse changeTcpServerStatus(@Validated @RequestBody TcpStatusRequest tcpStatusRequest) {

        tcpInterfaceService.changeTcpServerStatus(tcpStatusRequest.getId(), tcpStatusRequest.getStart());
        if (tcpStatusRequest.getStart()) {
            return new BaseResponse(translator.translate(ResultCode.SUCCESS_START_TCP_INTERFACE));
        }

        return new BaseResponse(translator.translate(ResultCode.SUCCESS_CLOSE_TCP_INTERFACE));

    }

    @PostMapping("/interface_tcp/update")
    @ResponseBody
    public BaseResponse update(
            @Validated(value = {TcpInterfaceBO.Update.class}) @RequestBody TcpInterfaceBO tcpInterfaceBO) {
        tcpInterfaceService.update(tcpInterfaceBO);
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_UPDATE_TCP_INTERFACE));
    }

    @PostMapping("/interface_tcp/delete")
    @ResponseBody
    public BaseResponse delete(
            @Validated(value = {TcpInterfaceBO.Update.class}) @RequestBody IdRequest idRequest) {
        tcpInterfaceService.delete(idRequest.getId());
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_DELETE_TCP_INTERFACE));
    }

}
