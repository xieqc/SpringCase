package com.xie.javacase.net.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.xie.javacase.net.netty.client.coder.XLClientDecoder;
import com.xie.javacase.net.netty.client.coder.XLClientEncoder;
import com.xie.javacase.net.netty.client.vo.XLRequest;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-18
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";  //args[0];
        int port = Integer.parseInt("8080");   //args[1]
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); //Bootstrap和ServerBootstrap比较像，只是Bootstrap主要用于客户端或无连接的channel
            b.group(workerGroup); //如果你只用了一个EventLoopGroup,那么它将被用在boos和worker上。只是客户端用不上bosss事件池
            b.channel(NioSocketChannel.class); //这里客户端采用NioSocketChannel而不是NioServerSocketChannel
            b.option(ChannelOption.SO_KEEPALIVE, true); //注意我们没有使用childOption()因为客户端SocketChannel没有父节点
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new DiscardClientHandler());
//                    ch.pipeline().addLast(new EchoClientHandler());
//                    ch.pipeline().addLast(new TimeClientHandler());
                    ch.pipeline().addLast(new XLClientEncoder(),new XLClientDecoder(),new XLClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); //我们应该调用connect()方法而不是bind()方法

            /* */
            XLRequest request = new XLRequest();
            request.setEncode((byte)0);
            request.setEncrypt((byte)0);
            request.setExtend1((byte)0);
            request.setExtend2((byte)0);
            request.setSessionid(0);
            request.setCommand(0);
            JSONObject j = new JSONObject();
            try {
                j.put("id",10005);
                j.put("name","王五");
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setParams(j.toString());
            f.channel().writeAndFlush(request);

            Thread.sleep(3000);//过快会造成未及时read就发送下个，结果只读取到一个混合的结果

            XLRequest request2 = new XLRequest();
            request2.setEncode((byte)0);
            request2.setEncrypt((byte)0);
            request2.setExtend1((byte)0);
            request2.setExtend2((byte)0);
            request2.setSessionid(0);
            request2.setCommand(0);
            JSONObject j2 = new JSONObject();
            try {
                j2.put("id",10006);
                j2.put("name","赵六");
            } catch (Exception e) {
                e.printStackTrace();
            }
            request2.setParams(j2.toString());
            f.channel().writeAndFlush(request2);
            Thread.sleep(3000);
            f.channel().close();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
