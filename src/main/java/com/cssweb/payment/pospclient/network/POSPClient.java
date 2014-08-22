package com.cssweb.payment.pospclient.network;



import com.cssweb.payment.pospclient.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
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
        // 设置域值
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
        // 设置域值结束

        // 处理位和域的映射
        BitFieldMap bfm = new BitFieldMap();

        // 注意：一定要按顺序写入
        bfm.addField(f7);
        bfm.addField(f11);
        bfm.addField(f33);
        bfm.addField(f39);
        bfm.addField(f70);

        // 返回域值字节数组
        byte[] dataByteArray = bfm.getData();
        System.out.println("dataByteArray = " + new String(dataByteArray));

        byte[] mainBitFieldMap = bfm.getMainBitFieldMap(); // 8字节长度的字节数组
        for (int i=0; i<mainBitFieldMap.length; i++)
        {
            String binaryStr  = BitUtil.byteToBinaryStr(mainBitFieldMap[i]);
            System.out.println("i=" + i + ", str=" + binaryStr);
        }

        byte[] extBitFieldMap = bfm.getExtBitFieldMap(); // 8字节长度的字节数组
        for (int i=0; i<extBitFieldMap.length; i++)
        {
            String binaryStr  = BitUtil.byteToBinaryStr(extBitFieldMap[i]);
            System.out.println("i=" + i + ", str=" + binaryStr);
        }
        // 处理位和域的映射结束


        // 开始处理消息类型
        MessageType msgType = new MessageType();
        msgType.setMsgType("0820");
        byte[] msgTypeByteArray = msgType.getMsgType();
        System.out.println("msgType len = " + msgTypeByteArray.length);


        // 开始处理消息头
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setMsgHeaderLen((byte)46);
        msgHeader.setVersion((byte)0b00000010);


        int totalLen = 0;
        if (bfm.hasExtBitFieldMap())
        {
            totalLen = MsgHeader.MSG_HEADER_LEN + msgTypeByteArray.length + mainBitFieldMap.length + extBitFieldMap.length + dataByteArray.length;
        }
        else
        {
            totalLen = MsgHeader.MSG_HEADER_LEN + msgTypeByteArray.length + mainBitFieldMap.length + dataByteArray.length;
        }
        msgHeader.setTotalLen(totalLen);


        msgHeader.setDestId("00010000");
        msgHeader.setSourceId("00010000");

        byte[] reserved = new byte[3];
        for (int i=0; i<reserved.length; i++)
            reserved[i] = 0;
        msgHeader.setReserved(reserved);

        msgHeader.setBatchNo((byte)0);

        msgHeader.setTradeInfo("00000000"); // 8字节定长

        msgHeader.setUserInfo((byte) 0);

        msgHeader.setRejectCode("00000"); // 5字节定长

        try {
            byte[] msgHeaderByteArray = msgHeader.toByteArray();
            System.out.println("消息头大小 = " + msgHeaderByteArray.length);
            if (msgHeaderByteArray.length != MsgHeader.MSG_HEADER_LEN)
            {
                System.out.println("消息头大小不符");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理消息头结束

    }


}
