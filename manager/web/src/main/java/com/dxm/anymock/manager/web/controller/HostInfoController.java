package com.dxm.anymock.manager.web.controller;

import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.manager.biz.model.response.DataResponse;
import com.dxm.anymock.manager.biz.model.response.dto.CoreHostInfoDTO;
import com.dxm.anymock.manager.biz.service.HostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.dxm.anymock.manager.web.WebConstants.URL_PREFIX_API_V2;

@Controller
@RequestMapping(URL_PREFIX_API_V2)
public class HostInfoController {
    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private ResultCodeTranslator translator;

    @PostMapping("/host_info/core")
    @ResponseBody
    public DataResponse<CoreHostInfoDTO> queryCoreHostInfo() {
        return new DataResponse<>(
                translator.translate(ResultCode.SUCCESS),
                hostInfoService.queryCoreHostInfo());
    }
}
