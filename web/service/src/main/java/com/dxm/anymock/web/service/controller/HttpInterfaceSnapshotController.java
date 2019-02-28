package com.dxm.anymock.web.service.controller;

import com.dxm.anymock.common.base.BaseResponse;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.CommonIdCheck;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.web.biz.HttpInterfaceService;
import com.dxm.anymock.web.biz.api.request.HttpInterfaceSnapshotPagedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(GlobalConstant.URL_PREFIX_API_V2)
public class HttpInterfaceSnapshotController {

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    @PostMapping("/interface_http_snapshot/selectByHttpInterfaceId")
    @ResponseBody
    public BaseResponse selectSnapshotByHttpInterfaceId(
            @Validated @RequestBody HttpInterfaceSnapshotPagedRequest request
    ) {
        return BaseResponse.success(httpInterfaceService.selectSnapshotByHttpInterfaceId(request));
    }

    @PostMapping("/interface_http_snapshot/selectById")
    @ResponseBody
    public BaseResponse selectSnapshotById(
            @Validated(value = CommonIdCheck.class) @RequestBody HttpInterfaceSnapshot httpInterfaceSnapshot
    ) {
        return BaseResponse.success(httpInterfaceService.selectSnapshotById(httpInterfaceSnapshot.getId()));
    }
}
