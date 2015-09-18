package com.xie.javacase.net.netty.client.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.xie.javacase.net.netty.client.vo.XLRequest;

/**
 * 服务器端编码器
 */
public class XLClientEncoder extends MessageToByteEncoder<XLRequest> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, XLRequest msg, ByteBuf out) throws Exception {
        XLRequest request = (XLRequest) msg;
		ByteBuf byteBuf = ctx.alloc().buffer();
        /**
         * 先组织报文头
         */
		byteBuf.writeByte(request.getEncode());
		byteBuf.writeByte(request.getEncrypt());
		byteBuf.writeByte(request.getExtend1());
		byteBuf.writeByte(request.getExtend2());
		byteBuf.writeInt(request.getSessionid());
		byteBuf.writeInt(request.getCommand());
		/**
		 * 组织报文的数据部分
		 */
        String sendString = request.getParams();
        byte[] sendBytes = sendString.getBytes("UTF8");
        int length = sendBytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeBytes(sendBytes);
//        String recString = new String( sendBytes ,"UTF-8");
//        System.out.println(recString);

		out.writeBytes(byteBuf);
	}
}
