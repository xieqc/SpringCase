package com.xie.javacase.net.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-10
 * Time: 下午2:50
 * 发送一个包含32位整数的时间消息到客户端，不需要接收客户端的消息并且消息发送完成后将断开连接
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { //当客户端和服务器的连接已建立将会调用channelActive()方法。在这方法中我们向客户端发送一个当前时间的32位整数
        final ByteBuf time = ctx.alloc().buffer(4); //要发送新消息，我们需要一个新的buffer来存放消息。我们要写入一个32位的整数，因此我们需要一个4字节大小的ByteBuf。我们通过ChannelHandlerContext.alloc()来获取当前的ByteBufAllocator并且申请一个新的buffer
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); //像以前一样，我们要发送消息，却没有flip操作。通过NIO发送消息之前不都需要调用java.nio.ByteBuffer.flip()方法吗？由于ByteBuf有2个指针所以不需要该方法；一个用于读操作另一个用于写操作。当你执行写操作时只会增加writer index而不会改变reader index。writer index和reader index互不影响。
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); //当write操作完成后会如何通知我们？只需要向ChannelFuture添加ChannelFutureListener。上面的例子中我们创建了一个新的匿名ChannelFutureListener来监听操作完成后关闭相应的Channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
