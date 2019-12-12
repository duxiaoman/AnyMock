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
import com.dxm.anymock.common.base.interceptor.MdcManager;
import com.dxm.anymock.common.dal.dao.InterfaceBranchDao;
import com.dxm.anymock.common.dal.dao.TcpInterfaceDao;
import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.common.dal.model.enums.Delimiter;
import com.dxm.anymock.core.biz.Delayer;
import com.dxm.anymock.core.biz.TcpMockContext;
import com.dxm.anymock.core.biz.service.TcpAsyncMockServer;
import groovy.lang.Binding;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY_TEMPLATE_SWITCH_CASE;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.TEXT;


/**
 * Created by jff on 2019/8/26.
 */
@Service
public class TcpServer implements DisposableBean {


    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);

    private static final Map<Integer, NettyServer> MAP = new HashMap<Integer, NettyServer>();

    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("jff test", CharsetUtil.UTF_8));

    @Autowired
    private GroovyServiceImpl groovyService;

    @Autowired
    private InterfaceBranchDao interfaceBranchDao;

    @Autowired
    private TcpAsyncMockServer tcpAsyncMockServer;


    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private TcpInterfaceDao tcpInterfaceDao;


    /**
     * 开启端口
     *
     * @param tcpInterfaceBO
     * @return
     */
    public boolean start(TcpInterfaceBO tcpInterfaceBO) {
        logger.info("start tcpserver, port:" + tcpInterfaceBO.getRequestUri() + ",name:" + tcpInterfaceBO.getName());

        try {
            int port = Integer.valueOf(tcpInterfaceBO.getRequestUri());
            NettyServer nettyServer = null;
            if ((nettyServer = MAP.get(port)) == null) {
                nettyServer = new NettyServer(tcpInterfaceBO, port);
                MAP.put(port, nettyServer);
            } else {
                throw new BizException(ResultCode.EXIST_TCP_SERVER);
            }

            nettyServer.start();
            return true;
        } catch (Exception e) {

        }

        return false;
    }


    public boolean stop(TcpInterfaceBO tcpInterfaceBO) {
        try {
            int port = Integer.valueOf(tcpInterfaceBO.getRequestUri());
            logger.info("stop tcp server,port={}", tcpInterfaceBO.getRequestUri());
            NettyServer ns = MAP.get(port);
            if (ns != null) {
                ns.destroy();
            }
            logger.info("stop tcp server:" + tcpInterfaceBO.getId() + ",name:" + tcpInterfaceBO.getName() + "sucesss");
            return true;
        } catch (Exception e) {
            logger.error("stop tcp server:" + tcpInterfaceBO.getId() + ",name:" + tcpInterfaceBO.getName() + "fail");
        }

        return false;
    }

    public boolean stop(String tcpPort) {
        try {
            logger.info("stop tcp server,port={}", tcpPort);
            int port = Integer.valueOf(tcpPort);
            NettyServer ns = MAP.get(port);
            if (ns != null) {
                ns.destroy();
            }
            logger.info("stop tcp server port:" + tcpPort + " sucesss");
            return true;
        } catch (Exception e) {
            logger.error("stop tcp serverport:" + tcpPort + " fail");
        }

        return false;
    }

    public class NettyServer {

        TcpInterfaceBO tcpInterfaceBO;
        Integer portNum;

        // 用来处理进来的连接
        private EventLoopGroup bossGroup = new NioEventLoopGroup(); //bossGroup就是parentGroup，是负责处理TCP/IP连接的
        // 用来处理已经被接收的连接,一旦bossGroup接收到连接,就会把连接信息注册到workerGroup上
        // workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件
        private EventLoopGroup workerGroup = new NioEventLoopGroup();

        //与客户端建立连接后得到的通道对象
        private Channel severChannel;

        private TcpMockContext context = new TcpMockContext();

        public NettyServer(TcpInterfaceBO tb, Integer port) {
            tcpInterfaceBO = tb;
            portNum = port;
        }

        public void start() {
            // 加载tcp接口数据
            context.setTcpInterfaceBO(tcpInterfaceBO);

            try {
                // 服务启动类
                ServerBootstrap sb = new ServerBootstrap();
                sb.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 128) //初始化服务端可连接队列,指定了队列的大小128
//                        .childOption(ChannelOption.SO_KEEPALIVE, true) //是否启用心跳保活机机制
                        .childHandler(new ChannelInitializer<SocketChannel>() {  // 绑定客户端连接时候触发操作
                            @Override
                            protected void initChannel(SocketChannel sh) throws Exception {
                                ByteBuf delimiter = Unpooled.copiedBuffer(Delimiter.DelimiterA.getCode().getBytes());
                                sh.pipeline()
//                                        .addLast("idle", new IdleStateHandler(3, 3, 3, TimeUnit.SECONDS)) //心跳600秒检测一次
                                        .addLast(new DelimiterBasedFrameDecoder(2048, delimiter))
                                        .addLast("serverHandler", new ServerHandler()); //使用ServerHandler类来处理接收到的消息 ,可以配置多个

                            }
                        });

                System.err.println("server 开启--------------");

                //绑定监听端口，调用sync同步阻塞方法等待绑定操作完
                ChannelFuture future = sb.bind(portNum).sync();

                if (future.isSuccess()) {
                    logger.info("TCP PORT:{" + portNum + "}start success!");
                    severChannel = future.channel();//获取通道
                } else {
                    future.cause().printStackTrace();
                    throw new SysException("bind port fail");
                }
            } catch (Exception e) {
                logger.error("TCP port:{" + portNum + "} start fail!", e);
                bossGroup.shutdownGracefully(); //关闭线程组
                workerGroup.shutdownGracefully();
            }
        }


        // 关闭当前netty server
        public void destroy() {
            logger.info("--------Netty tcp server shutdown start------");
            if (severChannel != null) {
                severChannel.close();
                severChannel = null;
            }

            try {
                Future<?> future = workerGroup.shutdownGracefully().await();
                if (!future.isSuccess()) {
                    logger.error("netty tcp workerGroup shutdown fail, {}", future.cause());
                }
                Future<?> future1 = bossGroup.shutdownGracefully().await();
                if (!future1.isSuccess()) {
                    logger.error("netty tcp bossGroup shutdown fail, {}", future1.cause());
                }
                MAP.remove(portNum);

            } catch (InterruptedException e) {
                logger.error("netty tcp bossGroup shutdown exception, {}", e);
            }
            logger.info("------Netty tcp server shutdown success------");

        }

        public class ServerHandler extends ChannelInboundHandlerAdapter {

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                logger.info("客户端端口{}连上了...", portNum);
            }

            //接受client发送的消息
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;

                // 创建一个和buf同等长度的字节数组
                byte[] reqBytes = new byte[buf.readableBytes()];
                buf.readBytes(reqBytes);

                handleMsg(ctx, reqBytes, context);
                logger.info("客户端端口{},服务端响应数据发送完毕...", portNum);
                ctx.channel().close();
            }

            //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                logger.info("服务端接收数据完毕..");
                ctx.flush();
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                ctx.close();
            }


            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                if (evt instanceof IdleStateEvent) {
                    IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

                    if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                        System.out.println("已经3秒没有收到信息！");
                        //向客户端发送消息
                        ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                } else {
                    super.userEventTriggered(ctx, evt);
                }
            }


            private void handleMsg(ChannelHandlerContext ctx, Object msg, TcpMockContext context) {
                try {
                    Delayer.delay(tcpInterfaceBO.getSyncDelay());
                    logger.info("Sync delay finished");

                    // 配置模式
                    ConfigMode configMode = tcpInterfaceBO.getConfigMode();
                    logger.info("ConfigMode = {}", configMode);

                    Binding binding = new Binding();
                    // Groovy脚本 绑定变量
                    if (configMode == GROOVY || configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
                        binding.setProperty("request", msg);
                        binding.setProperty("serverChannelHandlerContext", ctx);
                    }

                    Object responseBody;
                    if (configMode == TEXT) {
                        responseBody = tcpInterfaceBO.getResponseBody();
                        ctx.pipeline()
                                .addBefore("serverHandler", "encode", new StringEncoder());
                    } else if (configMode == GROOVY) {
                        responseBody = groovyService.exec(binding, tcpInterfaceBO.getSyncScript());
                    } else if (configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
                        String branchName = groovyService.exec(binding, tcpInterfaceBO.getBranchJumpScript()).toString();
                        logger.info("BranchName = {}", branchName);
                        InterfaceBranchBO branchBO = interfaceBranchDao.findBranch(branchName, tcpInterfaceBO.getBranchScriptList());
                        if (branchBO == null) {
                            throw new BizException(ResultCode.NOT_FOUND_INTERFACE_BRANCH);
                        }

                        responseBody = groovyService.exec(binding, branchBO.getSyncScript());
                    } else {
                        throw new SysException("Unknown ConfigMode");
                    }

                    // 同步返回
                    logger.info("ResponseBody = {}", responseBody);
                    ctx.writeAndFlush(responseBody);

                    // 异步处理
                    tcpAsyncCallback(context);

                } catch (Exception e) {
                    String error = " tcp interface[" + tcpInterfaceBO.getName() + "] mock fail!" + e;
                    logger.error(error);
                    ctx.writeAndFlush(error);
                }
            }
        }
    }


    public void tcpAsyncCallback(TcpMockContext context) {

        if (!BooleanUtils.isTrue(context.getTcpInterfaceBO().getNeedAsyncCallback())) {
            return;
        }

        String mdcTraceId = MDC.get(MdcManager.MDC_TRACE_ID_KEY);
        threadPoolTaskExecutor.execute(() -> {
            try {
                MDC.put(MdcManager.MDC_TRACE_ID_KEY, mdcTraceId);
                tcpAsyncMockServer.mock(context);
                MDC.clear();
            } catch (Exception e) {
                logger.warn("tcpAsyncCallback发生异常, ", e);
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        for (NettyServer ns : MAP.values()) {
            ns.destroy();
        }
    }
}
