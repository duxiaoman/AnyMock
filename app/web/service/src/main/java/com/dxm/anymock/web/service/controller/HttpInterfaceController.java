package com.dxm.anymock.web.service.controller;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.*;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.dal.dto.HttpInterfaceSnapshotDTO;
import com.dxm.anymock.web.biz.HttpInterfaceService;
import com.dxm.anymock.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(GlobalConstant.URL_PREFIX_API_V2)
public class HttpInterfaceController {

    @Autowired
    private HttpInterfaceService httpInterfaceService;

    @PostMapping("/interface_http/selectById")
    @ResponseBody
    public BaseResponse selectHttpInterfaceById(
            @Validated(value = CommonIdCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectById(httpInterface.getId()));
    }

    @PostMapping("/interface_http/selectBySpaceId")
    @ResponseBody
    public BaseResponse selectHttpInterfaceBySubSpaceId(
            @Validated(value = SpaceIdCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectBySpaceId(httpInterface.getSpaceId()));
    }

    @PostMapping("/interface_http/insert")
    @ResponseBody
    public BaseResponse insertHttpInterface(
            @Validated(value = {CommonInsertCheck.class}) @RequestBody HttpInterface httpInterface
    ) {
        httpInterface.setId(null);
        clearLastUpdateInfo(httpInterface);
        httpInterfaceService.insert(httpInterface);
        return new BaseResponse();
    }

    @PostMapping("/interface_http/update")
    @ResponseBody
    public BaseResponse updateHttpInterface(
            @Validated(value = {CommonUpdateCheck.class}) @RequestBody HttpInterface httpInterface
    ) {
        clearLastUpdateInfo(httpInterface);
        httpInterfaceService.update(httpInterface);
        return new BaseResponse();
    }

    @PostMapping("/interface_http/delete")
    @ResponseBody
    public BaseResponse deleteHttpInterface(
            @Validated(value = CommonDeleteCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.delete(httpInterface.getId());
        return new BaseResponse();
    }

    @PostMapping("/interface_http/start")
    @ResponseBody
    public BaseResponse startHttpInterface(
            @Validated(value = CommonIdCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.start(httpInterface.getId());
        return new BaseResponse();
    }

    @PostMapping("/interface_http/shutdown")
    @ResponseBody
    public BaseResponse shutdownHttpInterface(
            @Validated(value = CommonIdCheck.class) @RequestBody HttpInterface httpInterface
    ) {
        httpInterfaceService.shutdown(httpInterface.getId());
        return new BaseResponse();
    }

    // 仅作调试用，不对外发布
    @PostMapping("/interface_http/selectByRequestType")
    @ResponseBody
    public BaseResponse selectHttpInterfaceByRequestType(
            @Validated @RequestBody HttpInterface httpInterface
    ) {
        return new BaseResponse(httpInterfaceService.selectByRequestType(new RequestType(httpInterface)));
    }

    @PostMapping("/interface_http_snapshot/selectByHttpInterfaceId")
    @ResponseBody
    public BaseResponse selectSnapshotByHttpInterfaceId(
            @Validated(value = HttpInterfaceIdCheck.class) @RequestBody HttpInterfaceSnapshot httpInterfaceSnapshot
    ) {
        List<HttpInterfaceSnapshotDTO> snapshotDTOList = new LinkedList<>();
        httpInterfaceService.selectSnapshotByHttpInterfaceId(httpInterfaceSnapshot.getHttpInterfaceId())
                .forEach(snapshot -> snapshotDTOList.add(new HttpInterfaceSnapshotDTO(snapshot)));
        return new BaseResponse(snapshotDTOList);
    }

    @PostMapping("/interface_http_snapshot/selectById")
    @ResponseBody
    public BaseResponse selectSnapshotById(
            @Validated(value = CommonIdCheck.class) @RequestBody HttpInterfaceSnapshot httpInterfaceSnapshot
    ) {
        HttpInterfaceSnapshot snapshot = httpInterfaceService.selectSnapshotById(httpInterfaceSnapshot.getId());
        return new BaseResponse(new HttpInterfaceSnapshotDTO(snapshot));
    }

    private void clearLastUpdateInfo(HttpInterface httpInterface) {
        httpInterface.setLastUpdateTime(null);
        httpInterface.setLastUpdateUser(null);
    }
}
