package com.luoc.HelloWorld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/9/13 17:29
 */

    public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {


        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("HelloWorldClientHandler Active");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println("HelloWorldClientHandler read Message:" + msg);
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
