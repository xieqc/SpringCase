package com.xie.javacase.net.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-28
 * Time: 上午10:39
 * To change this template use File | Settings | File Templates.
 */
public class DiscardClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeChar('1');
        byteBuf.writeChar('2');
        byteBuf.writeChar('3');
        ChannelFuture f = ctx.writeAndFlush(byteBuf);
        f.addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
