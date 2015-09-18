package com.xie.javacase.net.netty.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import com.xie.javacase.net.netty.client.vo.XLRequest;
import com.xie.javacase.net.netty.client.vo.XLResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-21
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
public class XLClientHandler extends ChannelHandlerAdapter {
    XLRequest request = new XLRequest();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) { //ChannelHandler有两个和生命周期相关的listener：handlerAdded() 和 handlerRemoved(). 你可以在这两个方法中执行任何初始化或反初始化方法，但是不能长时间阻塞该方法
        /*
        request.setEncode((byte)0);
        request.setEncrypt((byte)0);
        request.setExtend1((byte)0);
        request.setExtend2((byte)0);
        request.setSessionid(0);
        request.setCommand(0);
        JSONObject j = new JSONObject();
        try {
            j.put("id",10003);
            j.put("name","张三");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setParams(j.toString());*/
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        //
    }

    /* 发起请求 */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
//        ctx.write(request);
//        ctx.flush();
    }
    /* 读取返回数据 */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        XLResponse m = (XLResponse) msg;
        System.out.println(m.getValues());
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
