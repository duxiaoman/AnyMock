package com.dxm.anymock.manager.integration.client;

/**
 * Created by jff on 2019/9/6.
 */
public interface TcpServiceClient {

    void startTcpService(String tcpPort);

    boolean changeTcpServerStatus(String interfaceUrl, boolean isStart);

    void deleteTcpService(String tcpPort);

    void updateTcpService(String oldTcpPort, String newTcpPort);
}
