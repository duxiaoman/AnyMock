package com.dxm.anymock.manager.web.controller;

import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.manager.biz.model.request.*;
import com.dxm.anymock.manager.biz.model.response.PagingDataResponse;
import com.dxm.anymock.manager.biz.model.response.dto.ConflictJudgementDTO;
import com.dxm.anymock.manager.biz.model.response.dto.HttpInterfaceDTO;
import com.dxm.anymock.manager.biz.service.HttpInterfaceService;
import com.dxm.anymock.manager.biz.model.response.BaseResponse;
import com.dxm.anymock.manager.biz.model.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.dxm.anymock.manager.web.WebConstants.URL_PREFIX_API_V2;

@Controller
@RequestMapping(URL_PREFIX_API_V2)
public class HttpInterfaceController {

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    @Autowired
    private ResultCodeTranslator translator;

    @PostMapping("/interface_http/conflictDetection")
    @ResponseBody
    public DataResponse<ConflictJudgementDTO> conflictDetection(
            @Validated @RequestBody HttpInterfaceConflictDetectionRequest request
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                httpInterfaceService.conflictDetection(request));
    }

    @PostMapping("/interface_http/selectById")
    @ResponseBody
    public DataResponse<HttpInterfaceDTO> queryById(
            @Validated @RequestBody IdRequest idRequest
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                httpInterfaceService.queryById(idRequest.getId()));
    }

    @PostMapping("/interface_http/selectBySpaceId")
    @ResponseBody
    public PagingDataResponse<HttpInterfaceDTO> queryBySpaceId(
            @Validated @RequestBody CriteriaPagingRequest<SpaceIdRequest> request
    ) {
        return new PagingDataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                httpInterfaceService.queryBySpaceId(request));
    }

    @PostMapping("/interface_http/selectAll")
    @ResponseBody
    public PagingDataResponse<HttpInterfaceDTO> queryAll(@Validated @RequestBody BasePagingRequest request) {
        return new PagingDataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                httpInterfaceService.queryAll(request));
    }

    @PostMapping("/interface_http/insert")
    @ResponseBody
    public DataResponse<HttpInterfaceDTO> create(
            @Validated(value = {HttpInterfaceBO.Insert.class}) @RequestBody HttpInterfaceBO httpInterfaceBO
    ) {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS_CREATE_HTTP_INTERFACE),
                httpInterfaceService.create(httpInterfaceBO));
    }

    @PostMapping("/interface_http/update")
    @ResponseBody
    public BaseResponse update(
            @Validated(value = {HttpInterfaceBO.Update.class}) @RequestBody HttpInterfaceBO httpInterfaceBO
    ) {
        httpInterfaceService.update(httpInterfaceBO);
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_UPDATE_HTTP_INTERFACE));
    }

    @PostMapping("/interface_http/delete")
    @ResponseBody
    public BaseResponse delete(@Validated @RequestBody IdRequest idRequest) {
        httpInterfaceService.delete(idRequest.getId());
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_DELETE_HTTP_INTERFACE));
    }
}
