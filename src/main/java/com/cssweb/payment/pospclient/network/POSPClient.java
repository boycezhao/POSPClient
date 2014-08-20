package com.cssweb.payment.pospclient.network;



import com.cssweb.payment.pospclient.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * Created by chenhf on 2014/7/14.
 */

public class POSPClient {
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

    public static void main(String[] args)
    {
        POSPClient client = new POSPClient();
        client.ping();
    }

    public void ping()
    {
        Field7 f7 = new Field7();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String tranTime = sdf.format(now);
        f7.setFieldValue(tranTime);

        Field11 f11 = new Field11();
        String traceNo = "";
        Random random = new Random();
        for (int i=0; i<6; i++) {
            traceNo += random.nextInt(10);
        }
        f11.setFieldValue(traceNo);


        Field33 f33 = new Field33();
        f33.setFieldValue("111111");


        Field39 f39 = new Field39();
        f39.setFieldValue("00");


        Field70 f70 = new Field70();
        f70.setFieldValue("301"); // 线路测试

        BitFieldMap bfm = new BitFieldMap();
        bfm.addField(f7);
        bfm.addField(f11);
        bfm.addField(f33);
        bfm.addField(f39);
        bfm.addField(f70);

        //byte[] bitmap = bfm.getBitFieldMap(); // 16字节长度的字节数组
        byte[] data = bfm.getData();
        System.out.println("data = " + new String(data));

    }
}
