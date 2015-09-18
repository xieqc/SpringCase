package com.xie.javacase.net.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-10
 * Time: 上午11:34
 * 无处理简单测试用例
 */
public class DiscardServerHandler extends ChannelHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        /* 查看收到的数据 */
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { //这个无效的循环等价于下面这行代码System.out.println(buf.toString(io.netty.util.CharsetUtil.US_ASCII))
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); //另一种做法就是调用in.release()方法
        }
        /* 回写信息给客户端
        ctx.write(msg); //调用write()方法将收到的数据逐字逐句的写入到网络I/O。Netty会在write操作完成后自动释放相关的消息
        ctx.flush(); //ctx.write()并不会立即完成,而是被缓存起来；当你调用ctx.flush()方法时消息才会被发送。另外，你也可以调用ctx.writeAndFlush(msg)方法一次性完成上面两个步骤
        */
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
