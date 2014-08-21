package com.cssweb.payment.pospclient;

/**
 * Created by chenhf on 2014/8/20.
 */
public class FieldMsgHeaderDest {
    private byte[] dest = new byte[11];

    public byte[] getDest() {
        return dest;
    }

    public void setDest(String destId) {

        StringBuffer sb = new StringBuffer();
        sb.append(destId);

        for (int i = destId.length(); i<11; i++)
        {
            sb.append(" "); // 不足11位，后补空格
        }

        dest = sb.toString().getBytes();
    }
}
