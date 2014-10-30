package com.cssweb.payment.posp.client;



import com.cssweb.payment.posp.business.*;
import com.cssweb.payment.posp.network.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by chenhf on 2014/7/14.
 */

public class POSPClient {
    private static final Logger logger =  LogManager.getLogger(POSPClient.class.getName());

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


    public boolean connect(String paymentServer, int port)
    {
        try {

            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new NettyClientInitializer());


            channel = b.connect(paymentServer, port).sync().channel();


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





    public void echo()
    {
        TestEcho test = new TestEcho();
        CustomMessage request = test.getRequest();
        handler.sendRequest(request);
    }

    public void getBalance()
    {
        TestGetBalance test = new TestGetBalance();
        CustomMessage request = test.getRequest();
        handler.sendRequest(request);
    }


}
