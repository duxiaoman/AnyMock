/**
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
import com.dxm.anymock.common.dal.model.SpaceBO;
import com.dxm.anymock.manager.biz.model.request.IdRequest;
import com.dxm.anymock.manager.biz.model.response.dto.SpaceDTO;
import com.dxm.anymock.manager.biz.service.SpaceService;
import com.dxm.anymock.manager.biz.model.response.BaseResponse;
import com.dxm.anymock.manager.biz.model.response.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.dxm.anymock.manager.web.WebConstants.URL_PREFIX_API_V2;

@Controller
@RequestMapping(URL_PREFIX_API_V2)
public class SpaceController {

    private static final Logger logger = LoggerFactory.getLogger(SpaceController.class);

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private ResultCodeTranslator translator;

    @PostMapping("/space/tree")
    @ResponseBody
    public DataResponse<List<SpaceDTO>> tree() {
        return new DataResponse<>(translator.translate(ResultCode.SUCCESS), spaceService.tree());
    }

    @PostMapping("/space/insert")
    @ResponseBody
    public BaseResponse create(
            @Validated(value = {SpaceBO.Insert.class}) @RequestBody SpaceBO spaceBO
    ) {
        spaceService.create(spaceBO);
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_CREATE_SPACE));
    }

    @PostMapping("/space/update")
    @ResponseBody
    public BaseResponse update(
            @Validated(value = {SpaceBO.Update.class}) @RequestBody SpaceBO spaceBO
    ) {
        spaceService.update(spaceBO);
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_UPDATE_SPACE));
    }

    @PostMapping("/space/delete")
    @ResponseBody
    public BaseResponse delete(
            @Validated @RequestBody IdRequest request
    ) {
        spaceService.delete(request.getId());
        return new BaseResponse(translator.translate(ResultCode.SUCCESS_DELETE_SPACE));
    }
}
