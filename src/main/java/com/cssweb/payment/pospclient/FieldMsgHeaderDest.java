package com.cssweb.payment.pospclient;

/**
 * Created by chenhf on 2014/8/20.
 */
public class FieldMsgHeaderDest {
    private byte[] dest = new byte[11];

    public byte[] getDest() {
        return dest;
    }

    public void setDest(byte[] dest) {
        this.dest = dest;
    }
}
