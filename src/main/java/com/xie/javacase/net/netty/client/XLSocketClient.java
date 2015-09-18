package com.xie.javacase.net.netty.client;

import com.xie.javacase.net.netty.client.vo.XLRequest;
import com.xie.javacase.net.netty.client.vo.XLResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-8-15
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class XLSocketClient {
	private static Selector selector;
	private static SocketChannel channel;

    public static void main(String[] args)throws Exception{

		/* 方式二：nio socketChannel 异步应答/请求*/
		selector = Selector.open();
		channel = SocketChannel.open(new InetSocketAddress("114.215.176.47", 8080));    //114.215.176.47
		channel.configureBlocking(false);	//false:非阻塞方式,true:传统的阻塞方式
		channel.register(selector, SelectionKey.OP_CONNECT);
		Thread connectThread = new Thread(new Runnable() {
			public void run() {
				connect();
			}
		});
		connectThread.start();
		//输出请求
		Thread.sleep(3000);
        int i=1;
		while(true) {
			XLRequest request = new XLRequest();
			request.setEncode((byte)0);
			request.setEncrypt((byte)0);
			request.setExtend1((byte)(i));
			request.setExtend2((byte)0);
			request.setSessionid(0);
			request.setCommand(0);

            int action = request.getExtend1();
            JSONObject j = new JSONObject();
            switch(action) {
                case 0: //注册
                    j.put("name","姓名"+(int)(Math.random()*10));
                    j.put("password","123456");
                    request.setParams(j.toString());
                    break;
                case 1: //登录
                    j.put("id",2);
                    j.put("name","姓名"+2);
                    j.put("password","123456");
                    request.setParams(j.toString());
                    break;
                case 2: //chat
                    j.put("fromid",2);
                    j.put("toid",1);
                    j.put("content","2 call 1");
                    request.setParams(j.toString());
                    break;
                default:
                    request.setParams("");
                    break;
            }

			final byte[] data = encode(request);

			ByteBuffer bf = ByteBuffer.wrap(data);
			channel.write(bf);
			System.out.println("send:"+request.getParams());
			Thread.sleep(3000);

            if(i==1) i++;
		}

		/* 方式一：socket 请求/应答
		XLRequest request = new XLRequest();
		request.setEncode((byte)0);
		request.setEncrypt((byte)0);
		request.setExtend1((byte)0);
		request.setExtend2((byte)0);
		request.setSessionid(0);
		request.setCommand(0);
		JSONObject j = new JSONObject();
		j.put("id",10005);
		j.put("name","王五"+(int)(Math.random()*10));
		request.setParams(j.toString());

		final byte[] data = encode(request);
		InetSocketAddress channel = new InetSocketAddress("localhost",8080);
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;
		try{
			socket = new Socket();
			socket.setSoLinger(true, 2);
			//socket.setKeepAlive(true); socket.setOOBInline(true); socket.setSoTimeout(40000);	//setSoTimeout就是定义心跳时间,此法用socket.isBound()|isClosed()|等来判断不一定准确；手工方法：socket.sendUrgentData(0xFF); // 写循环进程发送心跳包，报错则表示断开
			socket.setSendBufferSize(data.length);
			socket.setReceiveBufferSize(data.length);
			socket.setTcpNoDelay(true);
			socket.connect(channel);
			out = socket.getOutputStream();
			in = socket.getInputStream();
			int i=100;
			while(i>0) {
				//输出请求
				out.write(data);
				out.flush();
				//接收应答
				in = socket.getInputStream();
				XLResponse response = decode(in);
				System.out.println(response.getValues());
				Thread.sleep(100);
				i--;
			}
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			} if (socket != null) {
				socket.close();
			}
		}
*/
    }

	public static void connect() {
		try{
			while(true) {
				selector = Selector.open();
				channel.register(selector, SelectionKey.OP_READ);
//				System.out.println(selector.select()+"|"+selector.select());
				while (selector.select() > 0) {
					// 遍历每个有可用IO操作Channel对应的SelectionKey
					for (SelectionKey sk : selector.selectedKeys()) {
						// 如果该SelectionKey对应的Channel中有可读的数据
						if (sk.isReadable()) {
							// 使用NIO读取Channel中的数据
							SocketChannel sc = (SocketChannel) sk.channel();
							XLResponse response = decode(sc);
							System.out.println("receive:" + response.getValues());
						/*
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						sc.read(buffer);
						buffer.flip();
						// 将字节转化为为UTF-16的字符串
						String receivedString= Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
						// 控制台打印出来
						System.out.println("接收到来自服务器"+sc.socket().getRemoteSocketAddress()+"的信息:"+receivedString);
						*/
							// 为下一次读取作准备
							sk.interestOps(SelectionKey.OP_READ);
						}
						// 删除正在处理的SelectionKey
						selector.selectedKeys().remove(sk);
					}
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected static byte[] encode(XLRequest request) {
		String sendString = request.getParams();
		byte[] sendBytes = new byte[0];
		try {
			sendBytes = sendString.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int length = sendBytes.length;

		ByteBuffer bf = ByteBuffer.allocate(16+length);
		bf.put(request.getEncode());
		bf.put(request.getEncrypt());
		bf.put(request.getExtend1());
		bf.put(request.getExtend2());
		bf.putInt(request.getSessionid());
		bf.putInt(request.getCommand());
		bf.putInt(length);
		bf.put(sendBytes);

		return bf.array();
	}

	protected static XLResponse decode(InputStream in) {
		byte[] messageHead = new byte[16];
		try {
			in.read(messageHead, 0, 16);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer bf = ByteBuffer.allocate(16);
		bf.put(messageHead);

		XLResponse response = new XLResponse();
		response.setEncode(bf.get(0));
		response.setEncrypt(bf.get(1));
		response.setExtend1(bf.get(2));
		response.setExtend2(bf.get(3));
		response.setSessionid(bf.getInt(4));
		response.setResult(bf.getInt(8));
		response.setLength(bf.getInt(12));

		byte[] message = new byte[response.getLength()];
		try {
			in.read(message, 0, response.getLength());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			response.setValues(new String(message,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return response;
	}

	protected static XLResponse decode(SocketChannel chanel) {
		ByteBuffer bf = ByteBuffer.allocate(16);
		try {
			chanel.read(bf);
			bf.flip();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XLResponse response = new XLResponse();
		response.setEncode(bf.get(0));
		response.setEncrypt(bf.get(1));
		response.setExtend1(bf.get(2));
		response.setExtend2(bf.get(3));
		response.setSessionid(bf.getInt(4));
		response.setResult(bf.getInt(8));
		response.setLength(bf.getInt(12));

		ByteBuffer message = ByteBuffer.allocate(response.getLength());
		try {
			channel.read(message);
			bf.flip();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			response.setValues(new String(message.array(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return response;
	}
}
