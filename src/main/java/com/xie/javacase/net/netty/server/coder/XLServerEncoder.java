package com.xie.javacase.net.netty.server.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.xie.javacase.net.netty.server.vo.XLResponse;

/**
 * 服务器端编码器
 */
public class XLServerEncoder extends MessageToByteEncoder<XLResponse> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, XLResponse msg, ByteBuf out) throws Exception {
		XLResponse response = (XLResponse) msg;
		ByteBuf byteBuf = ctx.alloc().buffer();
        /**
         * 先组织报文头
         */
		byteBuf.writeByte(response.getEncode());
		byteBuf.writeByte(response.getEncrypt());
		byteBuf.writeByte(response.getExtend1());
		byteBuf.writeByte(response.getExtend2());
		byteBuf.writeInt(response.getSessionid());
		byteBuf.writeInt(response.getResult());
		/**
		 * 组织报文的数据部分
		 */
//		ByteBuf buf = Unpooled.copiedBuffer("you data", CharsetUtil.UTF_8);
//		int length = buf.readableBytes();//buf.capacity();
//		byteBuf.writeInt(length);
//		byteBuf.writeBytes(buf);

        String sendString = response.getValues();
        byte[] sendBytes = sendString.getBytes("UTF8");
        int length = sendBytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeBytes(sendBytes);
//        String recString=new String( sendBytes ,"UTF-8");
//        System.out.println(recString);

		out.writeBytes(byteBuf);
	}
}
