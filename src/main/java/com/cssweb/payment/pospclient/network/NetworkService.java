package com.cssweb.payment.pospclient.network;



import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

/**
 * Created by chenhf on 2014/7/14.
 */

public class NetworkService {
    private EventLoopGroup group = new NioEventLoopGroup();
    private Bootstrap b = new Bootstrap();
    private Channel channel = null;
    private NettyClientHandler handler = null;
    private boolean connected = false;
    private int connectRetry = 3;

    public String paymentServer;

    public String getPaymentServer() {
        return paymentServer;
    }

    public void setPaymentServer(String paymentServer) {
        this.paymentServer = paymentServer;
    }

    public boolean connect()
    {
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new NettyClientInitializer());

        try {
            channel = b.connect("127.0.0.1", 6000).sync().channel();


             handler =  (NettyClientHandler) channel.pipeline().last();

            connected = true;


        } catch (InterruptedException e) {
            e.printStackTrace();

            connected = false;
        }

        return connected;
    }

    public void close()
    {
        System.out.println("payment client 关闭.....................................");

        //ch.closeFuture().sync();
        channel.close();
        group.shutdownGracefully();
    }

    public void sendRequest(byte msgType, byte[] msgBody) {
        if (!connected )
        {
            connect();
        }

        handler.sendRequest(msgType, msgBody);
    }



    public CustomMessage recvResponse()
    {
        CustomMessage response = handler.recvResponse();
        return response;
    }
}
