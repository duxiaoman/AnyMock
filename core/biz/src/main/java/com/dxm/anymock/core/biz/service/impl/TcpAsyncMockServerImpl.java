/*
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


package com.dxm.anymock.core.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.exception.SysException;
import com.dxm.anymock.common.dal.dao.InterfaceBranchDao;
import com.dxm.anymock.common.dal.entity.InterfaceBranchDO;
import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.common.dal.model.enums.Delimiter;
import com.dxm.anymock.core.biz.Delayer;
import com.dxm.anymock.core.biz.TcpMockContext;
import com.dxm.anymock.core.biz.codec.protobuf.DemoReqProto;
import com.dxm.anymock.core.biz.service.GroovyService;
import com.dxm.anymock.core.biz.service.TcpAsyncMockServer;
import groovy.lang.Binding;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY_TEMPLATE_SWITCH_CASE;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.TEXT;

/**
 * Created by jff on 2019/8/30.
 */

@Service
public class TcpAsyncMockServerImpl implements TcpAsyncMockServer {

    private Logger logger = LoggerFactory.getLogger(TcpAsyncMockServerImpl.class);

    @Autowired
    private GroovyService groovyService;

    @Autowired
    private InterfaceBranchDao interfaceBranchDao;


    @Override
    public void mock(TcpMockContext context) {

        try {

            Delayer.delay(context.getTcpInterfaceBO().getAsyncDelay());
            logger.info("Async delay finished");

            NettyClient nettyClient = new NettyClient(context);
            nettyClient.start();
        } catch (Exception e) {
            logger.error("async error", e);
        }
    }

    public class NettyClient {
        private TcpMockContext tcpMockContext;

        NettyClient(TcpMockContext tm) {
            tcpMockContext = tm;
        }

        private NioEventLoopGroup group = new NioEventLoopGroup();

        public void start() {

            String[] callback = tcpMockContext.getTcpInterfaceBO().getCallbackRequestUrl().split(":");
            if (callback.length != 2) {
                throw new BizException(ResultCode.ILLEGAL_ARGUMENT);
            }
            String host = callback[0];
            int port = Integer.parseInt(callback[1]);

            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast("clientHandler", new ClientHandler()); //客户端处理类
                            }
                        });


                // 发起异步连接请求,绑定host和port信息
                ChannelFuture future = bootstrap.connect(host, port).sync();
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture arg0) throws Exception {
                        if (future.isSuccess()) {
                            logger.info("success to connect TCP, host={}, port={}", host, port);
                        } else {
                            logger.error("fail to connect TCP, host={}, port={}", host, port);
                            future.cause().printStackTrace();
                            group.shutdownGracefully();
                        }
                    }
                });
            } catch (Exception e) {
                logger.error("TCP port:{" + port + "}call back fail! ", e);
                group.shutdownGracefully();
            } finally {
                group.shutdownGracefully();
                logger.info("TCP port:{" + port + "} call back end, free NioEventLoopGroup! ");
            }
        }


        public class ClientHandler extends SimpleChannelInboundHandler {

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                logger.info("接口{},连接异步回执地址{} 通信异常！！", tcpMockContext.getTcpInterfaceBO().getRequestUri(),
                        tcpMockContext.getTcpInterfaceBO().getCallbackRequestUrl());
                cause.printStackTrace();
                ctx.close();
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                logger.info("接口{},连接异步回执地址 {} 成功", tcpMockContext.getTcpInterfaceBO().getRequestUri(),
                        tcpMockContext.getTcpInterfaceBO().getCallbackRequestUrl());
                send(ctx);
            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                logger.info("接口{},异步回执退出连接", tcpMockContext.getTcpInterfaceBO().getRequestUri());
                ctx.close();
            }

            @Override
            public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                logger.info("接受服务器数据：{}", msg);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                boolean active = ctx.channel().isActive();
                logger.info("client数据读取完成, [此时通道状态] {}", active);
                ctx.close();
            }

            public void send(ChannelHandlerContext ctx) {
                TcpInterfaceBO tcpInterfaceBO = tcpMockContext.getTcpInterfaceBO();

                // 发送请求
                ConfigMode configMode = tcpInterfaceBO.getConfigMode();
                logger.info("tcp asnyc, ConfigMode = {}", configMode);

                Binding binding = new Binding();
                // Groovy脚本 绑定变量
                if (configMode == GROOVY || configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
                    binding.setProperty("clientChannelHandlerContext", ctx);
                }

                Object aSyncRequest;
                if (configMode == TEXT) {
                    aSyncRequest = tcpInterfaceBO.getCallbackRequestBody();
                    ctx.pipeline().addBefore("clientHandler", "encode", new StringEncoder());
                } else if (configMode == GROOVY) {
                    aSyncRequest = groovyService.exec(binding, tcpInterfaceBO.getAsyncScript());
                } else if (configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
                    String branchName = groovyService.exec(binding, tcpInterfaceBO.getBranchJumpScript()).toString();
                    logger.info("BranchName = {}", branchName);
                    InterfaceBranchBO branchBO = interfaceBranchDao.findBranch(branchName, tcpInterfaceBO.getBranchScriptList());
                    if (branchBO == null) {
                        throw new BizException(ResultCode.NOT_FOUND_INTERFACE_BRANCH);
                    }

                    aSyncRequest = groovyService.exec(binding, branchBO.getAsyncScript());
                } else {
                    throw new SysException("Unknown ConfigMode");
                }

                // 异步返回
                logger.info("开始异步回调, tcp host:{} Async start, aSyncRequest = {}", tcpInterfaceBO.getRequestUri(), aSyncRequest);
                ctx.channel().writeAndFlush(aSyncRequest);
                // 增加结束标志
                ByteBuf byteBuf = Unpooled.copiedBuffer(Delimiter.DelimiterA.getCode().getBytes());
                ctx.writeAndFlush(byteBuf);

                logger.info("异步回调成功,TCP host:{}, Async TCP host:{}, ,content = {}",
                        tcpInterfaceBO.getRequestUri(), tcpInterfaceBO.getCallbackRequestUrl(), aSyncRequest);
            }

        }


    }

}
