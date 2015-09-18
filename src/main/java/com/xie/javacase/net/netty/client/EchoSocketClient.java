package com.xie.javacase.net.netty.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-8-15
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class EchoSocketClient {
    public static void main(String[] args)throws Exception{
        byte[] data = "hello test!中文内容".getBytes("UTF-8");
        InetSocketAddress channel = new InetSocketAddress("localhost",8080);
        Socket socket = null;
        OutputStream out = null;
        InputStream in = null;
        try{
            socket = new Socket();
            socket.setSoLinger(true, 2);
            //socket.setSoTimeout(2000);
            socket.setSendBufferSize(data.length);
            socket.setReceiveBufferSize(data.length);
            socket.setTcpNoDelay(true);
            socket.connect(channel);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            //输出请求
            out.write(data);
            out.flush();
            //接收应答
            in = socket.getInputStream();
            byte[] charBuf = new byte[data.length];
            int size = 0;
            size = in.read(charBuf, 0, data.length);
            System.out.println(new String(charBuf,"UTF-8"));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } if (socket != null) {
                try {
                    socket.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
