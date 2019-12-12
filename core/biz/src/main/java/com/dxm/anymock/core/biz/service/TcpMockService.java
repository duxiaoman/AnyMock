package com.dxm.anymock.core.biz.service;

import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;

/**
 * Created by jff on 2019/8/23.
 */
public interface TcpMockService {
    void startTcpServer(TcpInterfaceBO tcpInterfaceBO);

    boolean changeTcpServerStatus(TcpInterfaceBO tcpInterfaceBO, Boolean isStart);

    boolean delTcpServer(TcpInterfaceBO tcpInterfaceBO);

    boolean delTcpServer(String tcpPort);

    TcpInterfaceBO queryTcpInterfaceBO(TcpInterfaceKeyBO tcpInterfaceKeyBO);
}
