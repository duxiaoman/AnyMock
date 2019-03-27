package com.dxm.anymock.manager.biz.service.impl;

import com.dxm.anymock.manager.biz.model.response.dto.CoreHostInfoDTO;
import com.dxm.anymock.manager.biz.service.HostInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HostInfoServiceImpl implements HostInfoService {

    @Value("${anymock.core.host}")
    private String host;

    @Value("${anymock.core.http-interface-port}")
    private Integer httpInterfacePort;

    @Override
    public CoreHostInfoDTO queryCoreHostInfo() {
        CoreHostInfoDTO coreHostInfoDTO = new CoreHostInfoDTO();
        coreHostInfoDTO.setHost(host);
        coreHostInfoDTO.setHttpInterfacePort(httpInterfacePort);
        return coreHostInfoDTO;
    }
}
