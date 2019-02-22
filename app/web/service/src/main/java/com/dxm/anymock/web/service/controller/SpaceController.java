package com.dxm.anymock.web.service.controller;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.PrimaryKeyCheck;
import com.dxm.anymock.common.base.check.ValueCheck;
import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.web.biz.SpaceService;
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
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/space/list")
    @ResponseBody
    public BaseResponse listSpace() {
        return new BaseResponse(spaceService.list());
    }

    @PostMapping("/space/insert")
    @ResponseBody
    public BaseResponse insertSpace(
            @Validated(value = {ValueCheck.class}) @RequestBody Space space
    ) {
        space.setId(null);
        spaceService.insert(space);
        return new BaseResponse();
    }

    @PostMapping("/space/update")
    @ResponseBody
    public BaseResponse updateSpace(
            @Validated(value = {PrimaryKeyCheck.class, ValueCheck.class}) @RequestBody Space space
    ) {
        spaceService.update(space);
        return new BaseResponse();
    }

    @PostMapping("/space/delete")
    @ResponseBody
    public BaseResponse deleteSpace(@Validated(value = PrimaryKeyCheck.class) @RequestBody Space space) {
        spaceService.delete(space.getId());
        return new BaseResponse();
    }
}
