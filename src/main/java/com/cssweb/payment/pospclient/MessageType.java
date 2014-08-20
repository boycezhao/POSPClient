package com.cssweb.payment.pospclient;

import java.nio.ByteBuffer;

/**
 * Created by chenhf on 2014/7/21.
 */
public class    MessageType {
    public static  final int MSG_TYPE_LEN = 4;

    private byte msgCatalog; // 报文类
    private byte msgType; // 报文功能
    private byte tradeSender; // 交易发起方
    private byte reserved; // 保留

    //private byte[] msgType = new byte[MSG_TYPE_LEN];

    private ByteBuffer msgBuf;
}
