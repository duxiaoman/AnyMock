package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.web.biz.HostInfoService;
import com.dxm.anymock.web.biz.api.response.CoreHostInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HostInfoServiceImpl implements HostInfoService {

    @Value("${anymock.core.host}")
    private String host;

    @Value("${anymock.core.http-interface-port}")
    private Integer httpInterfacePort;

    @Override
    public CoreHostInfo selectCoreHostInfo() {
        CoreHostInfo coreHostInfo = new CoreHostInfo();
        coreHostInfo.setHost(host);
        coreHostInfo.setHttpInterfacePort(httpInterfacePort);
        return coreHostInfo;
    }
}
