package com.cssweb.payment.pospclient.network;

/**
 * Created by chenhf on 2014/8/22.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chenhf on 2014/7/21.
 */
public class MsgHeader {

    // 总共46字节

    public static final int MSG_HEADER_SIZE = 46;

    private byte msgHeaderLen;
    private byte version;
    private byte[] totalLen = new byte[4];
    private String destId;
    private String srcId;
    private byte[] reserved;
    private byte batchNo;
    private String tradeInfo;
    private byte userInfo;
    private String rejectCode;



    public boolean decodeMsgHeader(byte[] msgHeader)
    {
        return true;
    }

    public boolean encodeMsgHeader()
    {
        return true;
    }


    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(msgHeaderLen);
        baos.write(version);
        baos.write(totalLen);
        baos.write(destId.getBytes());
        baos.write(srcId.getBytes());
        baos.write(reserved);
        baos.write(batchNo);
        baos.write(tradeInfo.getBytes());
        baos.write(userInfo);
        baos.write(rejectCode.getBytes());



        return baos.toByteArray();
    }


    public void setMsgHeaderLen(byte msgHeaderLen)
    {
        this.msgHeaderLen = msgHeaderLen;
    }

    public void setVersion(byte version)
    {
        this.version = version;
    }

    public void setTotalLen(int totalLen)
    {
        String s = String.valueOf(totalLen);


        String str ="0000";

        String val  = str.substring(0, str.length() - s.length()) + s;

        this.totalLen = val.getBytes();
    }

    public void setDestId(String destId)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(destId);

        for (int i = destId.length(); i<11; i++)
        {
            sb.append(" "); // 不足11位，后补空格
        }

        this.destId = sb.toString();
    }

    public void setSourceId(String srcId)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(srcId);

        for (int i = srcId.length(); i<11; i++)
        {
            sb.append(" "); // 不足11位，后补空格
        }


        this.srcId = sb.toString();
    }

    public void setReserved(byte[] reserved)
    {
        this.reserved = reserved;
    }

    public void setBatchNo(byte batchNo)
    {
        this.batchNo = batchNo;
    }

    public void setTradeInfo(String tradeInfo)
    {
        this.tradeInfo = tradeInfo;
    }
    public void setUserInfo(byte userInfo)
    {
        this.userInfo = userInfo;
    }

    public void setRejectCode(String rejectCode)
    {
        this.rejectCode = rejectCode;
    }

}