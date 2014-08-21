package com.cssweb.payment.pospclient;

/**
 * Created by chenhf on 2014/8/20.
 */
public class FieldMsgHeaderTotalLen {

       private byte[] totalLen = new byte[4];

    public void setTotalLen(int len) {

        String s = String.valueOf(len);


        String str ="0000";

        String val  = str.substring(0, str.length() - s.length()) + s;

        totalLen = val.getBytes();
    }

    public byte[] getTotalLen()
    {
        return totalLen;
    }
}
