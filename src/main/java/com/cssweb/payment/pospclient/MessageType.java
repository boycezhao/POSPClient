package com.cssweb.payment.pospclient;

import java.nio.ByteBuffer;

/**
 * Created by chenhf on 2014/7/21.
 */
public class    MessageType {
    private String msgType;

    public byte[] getMsgType() {
        return msgType.getBytes();
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
