package com.dxm.anymock.core.biz.service;

import com.dxm.anymock.core.biz.TcpMockContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by jff on 2019/8/30.
 */
public interface TcpAsyncMockServer {

    void mock(TcpMockContext context);
}
