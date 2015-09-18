package com.xie.javacase.net.netty.server.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import com.xie.javacase.net.netty.server.vo.XLRequest;

import java.util.List;

public class XLServerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 16) {
			return;
		}
		in.markReaderIndex();
		byte encode = in.readByte();
		byte encrypt = in.readByte();
		byte extend1 = in.readByte();
		byte extend2 = in.readByte();
		int sessionid = in.readInt();
		int command = in.readInt();
		int length = in.readInt(); // 数据包长
		if (in.readableBytes() < length) {
			in.resetReaderIndex();
			return;
		}
		XLRequest request = new XLRequest();
        request.setEncode(encode);
        request.setEncrypt(encrypt);
        request.setExtend1(extend1);
        request.setExtend2(extend2);
        request.setSessionid(sessionid);
        request.setCommand(command);
        request.setLength(length);

        ByteBuf dataBuffer = ctx.alloc().buffer();

//		in.readBytes(dataBuffer, length);
        int l = length;//in.readableBytes();
        byte b[] = new byte[l];
        for(int i=0;i<l;i++) {
            b[i] =  in.readByte();
        }
        String params = new String(b,"UTF-8");
        request.setParams(params);
        out.add(request);  //当decode()向out添加数据时，表明decoder已成功处理一个消息；这时候ByteToMessageDecoder 会丢弃缓冲区中已处理成功的数据。请记住你不需要处理多个消息，ByteToMessageDecoder 会多次调用decode()方法直到没有消息可以处理
	}

}