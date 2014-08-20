package com.cssweb.payment.pospclient;

/**
 * Created by chenhf on 2014/8/20.
 */
public class FieldMsgHeaderTotalLen {

    private byte[] totalLen = new byte[4];

    public byte[] getTotalLen() {
        return totalLen;
    }

    public void setTotalLen(byte[] totalLen) {
        this.totalLen = totalLen;
    }
}
