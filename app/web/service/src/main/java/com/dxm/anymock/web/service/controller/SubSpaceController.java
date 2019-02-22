package com.dxm.anymock.web.service.controller;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.PrimaryKeyCheck;
import com.dxm.anymock.common.base.check.ValueCheck;
import com.dxm.anymock.common.base.entity.SubSpace;
import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.web.biz.SubSpaceService;
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
public class SubSpaceController {

    @Autowired
    private SubSpaceService subSpaceService;

    @PostMapping("/subspace/insert")
    @ResponseBody
    public BaseResponse insertSubSpace(
            @Validated(value = {ValueCheck.class}) @RequestBody SubSpace subSpace
    ) {
        subSpace.setId(null);
        subSpaceService.insert(ConvertUtils.convert(subSpace, SubSpace.class));
        return new BaseResponse();
    }

    @PostMapping("/subspace/update")
    @ResponseBody
    public BaseResponse updateSubSpace(
            @Validated(value = {PrimaryKeyCheck.class, ValueCheck.class}) @RequestBody SubSpace subSpace
    ) {
        subSpaceService.update(ConvertUtils.convert(subSpace, SubSpace.class));
        return new BaseResponse();
    }

    @PostMapping("/subspace/delete")
    @ResponseBody
    public BaseResponse deleteSubSpace(
            @Validated(value = PrimaryKeyCheck.class) @RequestBody SubSpace subSpace
    ) {
        subSpaceService.delete(subSpace.getId());
        return new BaseResponse();
    }
}
