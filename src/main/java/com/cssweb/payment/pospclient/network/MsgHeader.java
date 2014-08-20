package com.cssweb.payment.pospclient.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by chenhf on 14-1-4.
 */
public class MsgHeader {
    private static final Logger logger = LogManager
            .getLogger(MsgHeader.class.getName());

    private int msgContentSize;
    private byte msgType;
    private int crc;
    private byte priority; // 优先级
    private byte zip;
    private byte encrypt;
    public final static int MSG_HEADER_SIZE = 12; // 4+1+4+1+1+1=12


    public byte getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(byte encrypt) {
        this.encrypt = encrypt;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }


    public int getMsgContentSize() {

        return msgContentSize;
    }

    public void setMsgContentSize(int msgContentSize) {
        this.msgContentSize = msgContentSize;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte getZip() {
        return zip;
    }

    public void setZip(byte zip) {
        this.zip = zip;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }


    public void encodeMsgHeader(int msgContentSize, byte msgType, int crc, byte zip, byte encrypt, byte priority)  {
        setMsgContentSize(msgContentSize);
        setMsgType(msgType);
        setCrc(crc);
        setZip(zip);
        setEncrypt(encrypt);
        setPriority(priority);

        //return toByteArray();
    }

    public byte[] toByteArray() throws IOException {
        // 可以使用java aio ByteBuffer
        // 可以使用netty ByteBuf

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);

        byte[] temp = Util.ntoh(msgContentSize, 4); // 新版本
        // byte[] temp =  Util.hton(msgContentSize); // 老版本
        out.write(temp, 0, 4);


        out.writeByte(msgType);

        out.writeInt(crc);

        out.writeByte(priority);

        out.writeByte(zip);
        out.writeByte(encrypt);



        byte[] msgHeader = buf.toByteArray();

        out.close();
        buf.close();



        return msgHeader;
    }




    /*
    解析消息头
     */
    public void decodeMsgHeader(byte[] msgHeader) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(msgHeader);
        DataInputStream in = new DataInputStream(buf);

        byte[] size = new byte[4];
        in.readFully(size);
        msgContentSize = Util.hton(size); // 新版本

      //  msgContentSize = Util.ntoh(size); // 老版本
        logger.info("消息内容大小" + msgContentSize);

        msgType = in.readByte();

        crc = in.readInt();

        priority = in.readByte();

        zip = in.readByte();
        encrypt = in.readByte();



        in.close();
        buf.close();
    }
}
