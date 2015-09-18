package com.xie.javacase.net.netty.client.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import com.xie.javacase.net.netty.client.vo.XLResponse;

import java.util.List;

public class XLClientDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 16) {
			return; //当累积缓存中的数据不满足处理要求时decode()不需要向out中添加任何东西。当有更多数据到来时ByteToMessageDecoder会再次调用decode()方法
		}
		byte encode = in.readByte();
		byte encrypt = in.readByte();
		byte extend1 = in.readByte();
		byte extend2 = in.readByte();
		int sessionid = in.readInt();
		int result = in.readInt();
		int length = in.readInt(); // 数据包长
		if (in.readableBytes() < length) {
			in.resetReaderIndex();
			return;
		}
        XLResponse response = new XLResponse();
        response.setEncode(encode);
        response.setEncrypt(encrypt);
        response.setExtend1(extend1);
        response.setExtend2(extend2);
        response.setSessionid(sessionid);
        response.setResult(result);
        response.setLength(length);

		ByteBuf dataBuffer = ctx.alloc().buffer();

//		in.readBytes(dataBuffer, length);
        int l= in.readableBytes();
        byte b[] = new byte[l];
        for(int i=0;i<l;i++) {
            b[i] =  in.readByte();
        }
		String values = new String(b,"UTF-8");
        response.setValues(values);
		out.add(response);  //当decode()向out添加数据时，表明decoder已成功处理一个消息；这时候ByteToMessageDecoder 会丢弃缓冲区中已处理成功的数据。请记住你不需要处理多个消息，ByteToMessageDecoder 会多次调用decode()方法直到没有消息可以处理
	}

}