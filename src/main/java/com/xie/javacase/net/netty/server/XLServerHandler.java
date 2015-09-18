package com.xie.javacase.net.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import com.xie.javacase.net.netty.server.vo.XLRequest;
import com.xie.javacase.net.netty.server.vo.XLResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-22
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class XLServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        /*
        XLResponse response = new XLResponse();
            response.setEncode((byte)0);
            response.setEncrypt((byte)0);
            response.setExtend1((byte)0);
            response.setExtend2((byte)0);
        ChannelFuture f = ctx.writeAndFlush(response);
        f.addListener(ChannelFutureListener.CLOSE);
        */
        // closed on shutdown.
        NettyServer.allChannels.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        XLRequest request = (XLRequest) msg;
		request.setIp(ctx.channel().remoteAddress().toString());
        System.out.println(request.getIp()+":"+request.getParams());
		JSONObject reqObj = new JSONObject();
		try {
			reqObj = new JSONObject(request.getParams());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		XLResponse response = new XLResponse();
        response.setEncode((byte)0);
        response.setEncrypt((byte)0);
        response.setExtend1((byte)0);
        response.setExtend2((byte)0);
        response.setValues(reqObj.toString());
        ChannelFuture f = ctx.writeAndFlush(response);
//        f.addListener(ChannelFutureListener.CLOSE);
		// Send the received message to all channels but the current one.
		System.out.println(NettyServer.allChannels.size());
		for (Channel c: NettyServer.allChannels) {
			if (c != ctx.channel()) {
				System.out.println("other:"+response.getValues());
				c.writeAndFlush(response);
			}
		}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
