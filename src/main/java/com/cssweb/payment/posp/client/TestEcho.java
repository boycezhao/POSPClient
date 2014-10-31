package com.cssweb.payment.posp.client;

import com.cssweb.payment.posp.business.*;
import com.cssweb.payment.posp.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhf on 2014/10/30.
 */
public class TestEcho {
    private static final Logger logger =  LogManager.getLogger(TestEcho.class.getName());

    public CustomMessage getRequest()
    {
        CustomMessage request = new CustomMessage();


        MsgHeader msgHeader = new MsgHeader();
        MessageType msgType = new MessageType();
        BitFieldMap bitFieldMap = new BitFieldMap();
        FieldData fieldData = new FieldData();

        // 设置域值
        List<Field> fields = new ArrayList<Field>();

        try {


            Field7 f7 = new Field7();
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
            String tranTime = sdf.format(now);
            f7.setData(tranTime);

            Field11 f11 = new Field11();
            String traceNo = "";
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                traceNo += random.nextInt(10);
            }
            f11.setData(traceNo);

            Field33 f33 = new Field33();
            f33.setData("111111");

            Field39 f39 = new Field39();
            f39.setData("00");

            Field70 f70 = new Field70();
            f70.setData("301"); // 线路测试


            fields.add(f7);
            fields.add(f11);
            fields.add(f33);
            fields.add(f39);
            fields.add(f70);
            // 设置域值结束



        // 开始处理消息类型
        msgType.setMsgType("0820");
        request.setMsgType(msgType);
        logger.info("msgType = " + new String(msgType.getMsgType()));
        // 结束处理消息类型



        // 设置位图
        bitFieldMap.setFields(fields);
        request.setBitFieldMap(bitFieldMap);

        logger.info("array = " + bitFieldMap.getArrayStr());




            // 设置域值
            fieldData.encode(fields);
            request.setFieldData(fieldData);
            logger.info("fieldData = " + new String(fieldData.getFieldData()));

            // 设置消息头
            int totalLen = MsgHeader.MSG_HEADER_SIZE + MessageType.MSG_TYPE_SIZE + bitFieldMap.getBitFieldMapLen() + fieldData.getFieldDataLen();
            logger.info("totalLen = " + totalLen);
            msgHeader.encode(totalLen, "00010000", "00010000", (byte)0, "00000000", (byte)0, "00000");
            request.setMsgHeader(msgHeader);

            // 消息编码,这一步非常重要，把msgType, bitFieldMap, fieldData合成msgContent
            request.encode();

            return request;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FieldLengthException e) {
            e.printStackTrace();
        } catch (OverflowMaxLengthException e) {
            e.printStackTrace();
        }


        return null;

    }
}
