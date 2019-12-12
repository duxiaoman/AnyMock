package com.dxm.anymock.manager.integration.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

public class Constants {

    /* 开启tcp服务url */
    public static final String START_TCP_SERVICE_URL = "/anymock/tcp/startTcpService";

    /* 删除tcp服务url */
    public static final String DEL_TCP_SERVICE_URL = "/anymock/tcp/delTcpService";

    /* 变更tcp服务状态url */
    public static final String CHANGE_TCP_SERVICE_STATUS_URL = "/anymock/tcp/changeTcpServerStatus";

    public static final String UPDATE_TCP_SERVICE_URL = "/anymock/tcp/updateTcpService";

}
