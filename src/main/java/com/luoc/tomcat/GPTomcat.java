package com.luoc.tomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/9/14 7:47
 */
public class GPTomcat {

    public void start(int port) throws Exception {
        // boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // netty 服务
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(bossGroup, workerGroup)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //无锁化串行编程

                            // 业务逻辑连路 编码器
                            ch.pipeline().addLast(new HttpRequestEncoder());
                            // 解码器
                            ch.pipeline().addLast(new HttpRequestDecoder());

                            // 业务处理逻辑
                            ch.pipeline().addLast(new GPtomcatHandler());

                        }
                    })

                    //配置信息
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture cf = bootstrap.bind(port).sync();

            System.out.println("Tomcat 已经启动，端口：" + port);

            cf.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new GPTomcat().start(8888);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
