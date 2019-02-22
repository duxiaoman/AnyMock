package com.dxm.anymock.web.service.controller;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.PrimaryKeyCheck;
import com.dxm.anymock.common.base.check.SubSpaceIdCheck;
import com.dxm.anymock.common.base.check.ValueCheck;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.web.biz.HttpInterfaceService;
import com.dxm.anymock.web.service.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(GlobalConstant.URL_PREFIX_API_V2)
public class HttpInterfaceController {

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    @PostMapping("/interface/http/selectById")
    @ResponseBody
    public BaseResponse selectHttpInterfaceById(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectById(httpInterface.getId()));
    }

    @PostMapping("/interface/http/selectBySubSpaceId")
    @ResponseBody
    public BaseResponse selectHttpInterfaceBySubSpaceId(
            @Validated(value = SubSpaceIdCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectBySubSpaceId(httpInterface.getSubSpaceId()));
    }

    @PostMapping("/interface/http/insert")
    @ResponseBody
    public BaseResponse insertHttpInterface(
            @Validated(value = {ValueCheck.class}) @RequestBody HttpInterface httpInterface
    ) {
        httpInterface.setId(null);
        clearLastUpdateInfo(httpInterface);
        httpInterfaceService.insert(httpInterface);
        return new BaseResponse();
    }

    @PostMapping("/interface/http/update")
    @ResponseBody
    public BaseResponse updateHttpInterface(
            @Validated(value = {PrimaryKeyCheck.class, ValueCheck.class}) @RequestBody HttpInterface httpInterface
    ) {
        clearLastUpdateInfo(httpInterface);
        httpInterfaceService.update(httpInterface);
        return new BaseResponse();
    }

    @PostMapping("/interface/http/delete")
    @ResponseBody
    public BaseResponse deleteHttpInterface(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.delete(httpInterface.getId());
        return new BaseResponse();
    }

    @PostMapping("/interface/http/start")
    @ResponseBody
    public BaseResponse startHttpInterface(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.start(httpInterface.getId());
        return new BaseResponse();
    }

    @PostMapping("/interface/http/shutdown")
    @ResponseBody
    public BaseResponse shutdownHttpInterface(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.shutdown(httpInterface.getId());
        return new BaseResponse();
    }

    @PostMapping("/interface/http/selectByRequestType")
    @ResponseBody
    public BaseResponse selectHttpInterfaceByRequestType(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectByRequestType(new RequestType(httpInterface)));
    }

    private void clearLastUpdateInfo(HttpInterface httpInterface) {
        httpInterface.setLastUpdateTime(null);
        httpInterface.setLastUpdateUser(null);
    }
}
