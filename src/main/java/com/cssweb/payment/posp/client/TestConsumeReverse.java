package com.cssweb.payment.posp.client;

import com.cssweb.payment.posp.business.*;
import com.cssweb.payment.posp.network.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhf on 2014/11/3.
 */
public class TestConsumeReverse {
    public CustomMessage getRequest() {
        Date now = new Date();
        StringBuffer sb = new StringBuffer();

        String AC_ID = "00000000000";
        String SW_ID = "11111111111";
        String IS_ID = "22222222222";
        String referNo = "181030414357"; // 参考号
        String terminalNo = "63856007"; // 终端号
        String batchNo = "000216"; // 批次号
        String authNo = "571462"; // 授权码
        String voucherNo = "005572"; // 凭证号
        String merchantNo = "102310058125081"; // 商户编号


        CustomMessage request = new CustomMessage();

        MsgHeader msgHeader = new MsgHeader();
        MessageType msgType = new MessageType();
        BitFieldMap bitFieldMap = new BitFieldMap();
        FieldData fieldData = new FieldData();
        List<Field> fields = new ArrayList<Field>();

        try {


            // 主账号
            Field2 field2 = new Field2();
            field2.setData("6226090217946181");
            System.out.println(field2.toString());
            fields.add(field2);


            //交易处理码
            Field3 field3 = new Field3();
            //301000
            //String f3 = Field3.TRD_TYPE_QUERY_SERVICE + Field3.FROM_DEPOSIT + Field3.FROM_DEFAULT + Field3.TO_DEFAULT + Field3.TO_DEFAULT;
            field3.setData("00x000");
            //field3.setTradeType(Field3.TRD_TYPE_QUERY_SERVICE);
            //field3.setFrom(Field3.FROM_DEPOSIT + Field3.FROM_DEFAULT);
            //field3.setTo(Field3.TO_DEFAULT + Field3.TO_DEFAULT);
            System.out.println(field3.toString());
            fields.add(field3);

            //交易金额
            Field4 field4 = new Field4();
            field4.setData("000000100002");
            System.out.println(field4.toString());
            fields.add(field4);

            //清算金额
            Field5 field5 = new Field5();
            //持卡人扣账金额
            Field6 field6 = new Field6();

            // 交易传输时间
            Field7 field7 = new Field7();
            SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
            String tranTime = sdf.format(now);
            field7.setData(tranTime);
            System.out.println(field7.toString());
            fields.add(field7);


            //清算汇率
            Field9 field9 = new Field9();
            //持卡人扣账汇率
            Field10 field10 = new Field10();

            //系统跟踪号
            Field11 field11 = new Field11();
            String traceNo = "";
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                traceNo += random.nextInt(10);
            }
            field11.setData(traceNo);
            System.out.println(field11.toString());
            fields.add(field11);

            //受卡方所在地时间
            Field12 field12 = new Field12();
            SimpleDateFormat time = new SimpleDateFormat("HHmmss");
            String t = time.format(now);
            field12.setData(t);
            System.out.println(field12.toString());
            fields.add(field12);

            //受卡方所在地日期
            Field13 field13 = new Field13();
            SimpleDateFormat date = new SimpleDateFormat("MMdd");
            String d = date.format(now);
            field13.setData(d);
            System.out.println(field13.toString());
            fields.add(field13);



            // 清算日期
            Field15 field15 = new Field15();
            //SimpleDateFormat date = new SimpleDateFormat("MMdd");
            //String d = date.format(now);
            field15.setData(d);
            System.out.println(field15.toString());
            fields.add(field15);


            // 商户类型
            //取值请参见GB/T 20548-2006《金融零售业务 商户类别代码》
            Field18 field18 = new Field18();
            field18.setData("1234");
            System.out.println(field18.toString());
            fields.add(field18);


            //服务点输入方式码
            Field22 field22 = new Field22();
            String f22 = Field22.PAN_IC + Field22.PIN_INCLUDE;
            field22.setData(f22);
            //field22.setPAN(Field22.PAN_IC);
            //field22.setPIN(Field22.PIN_INCLUDE);
            System.out.println(field22.toString());
            fields.add(field22);

            //服务点条件码
            Field25 field25 = new Field25();
            field25.setData(Field25.COMMIT);
            System.out.println(field25.toString());
            fields.add(field25);



            //受理机构标识码
            Field32 field32 = new Field32();
            field32.setData(AC_ID);
            System.out.println(field32.toString());
            fields.add(field32);

            //发送机构标识码
            Field33 field33 = new Field33();
            field33.setData(IS_ID);
            System.out.println(field33.toString());
            fields.add(field33);




            //检索参考号
            Field37 field37 = new Field37();
            field37.setData(referNo);
            System.out.println(field37.toString());
            fields.add(field37);

            //授权标识应答码
            Field38 field38 = new Field38();
            //fields.add(field38);


            //受卡机终端标识码
            Field41 field41 = new Field41();
            field41.setData(terminalNo);
            System.out.println(field41.toString());
            fields.add(field41);

            //受卡方标识码
            Field42 field42 = new Field42();
            field42.setData(merchantNo);
            System.out.println(field42.toString());
            fields.add(field42);

            //受卡方名称地址
            Field43 field43 = new Field43();
            String addr = field43.appendSpace("地址", field43.getDataLen());
            field43.setData(addr);
            System.out.println(field43.toString());
            fields.add(field43);





            //交易货币代码
            //参见ISO 4217标准
            Field49 field49 = new Field49();
            field49.setData("CNY");
            System.out.println(field49.toString());
            fields.add(field49);

            //原始数据元
            Field90 field90 = new Field90();

            Field90_1 field90_1 = new Field90_1();
            field90_1.setData("msgt");
            System.out.println(field90_1.toString());

            Field90_2 field90_2 = new Field90_2();
            field90_2.setData(field11.getData());
            System.out.println(field90_2.toString());

            Field90_3 field90_3 = new Field90_3();
            field90_3.setData(field7.getData());
            System.out.println(field90_3.toString());

            Field90_4 field90_4 = new Field90_4();
            field90_4.setData(field32.getData());
            System.out.println(field90_4.toString());

            Field90_5 field90_5 = new Field90_5();
            field90_5.setData(field33.getData());
            System.out.println(field90_5.toString());

            field90.addSubField(field90_1);
            field90.addSubField(field90_2);
            field90.addSubField(field90_3);
            field90.addSubField(field90_4);
            field90.addSubField(field90_5);

            field90.encode();

            System.out.println(field90.toString());
            fields.add(field90);



            //接收机构标识码
            Field100 field100 = new Field100();
            field100.setData(SW_ID);
            System.out.println(field100.toString());
            fields.add(field100);


            //预付卡发卡机构保留
            //预付卡发卡机构保留
            Field121 field121 = new Field121();

            //应答/应答原因码
            Field121_1 field121_1 = new Field121_1();
            field121_1.setData(Field121_1.RC_ISSUER_RESPONSE);
            System.out.println(field121_1.toString());


            //单/双或双/单转换码
            Field121_2 field121_2 = new Field121_2();
            field121_2.setData(Field121_2.CC_UNKNOWN);
            System.out.println(field121_2.toString());

            //卡性质
            Field121_3 field121_3 = new Field121_3();
            field121_3.setData(Field121_3.CARD_TYPE_CUP_DEBIT);
            System.out.println(field121_3.toString());

            //预付卡发卡机构保留
            Field121_4 field121_4 = new Field121_4();
            sb.delete(0, sb.length());
            for (int i = 0; i < field121_4.getDataLen(); i++) {
                sb.append('0');
            }
            field121_4.setData(sb.toString().getBytes());
            System.out.println(field121_4.toString());


            Field121_5_ID field121_5_id = new Field121_5_ID();
            sb.delete(0, sb.length());
            for (int i = 0; i < field121_5_id.getDataLen(); i++) {
                sb.append('1');
            }
            field121_5_id.setData(sb.toString().getBytes());
            System.out.println(field121_5_id.toString());

            //转入和转出方标识代码/手续费信息
            Field121_5 field121_5 = new Field121_5();
            field121_5.addSubFieldByTag(field121_5_id);
            //field121_5.addField(field121_5_id);
            System.out.println(field121_5.toString());


            field121.addSubField(field121_1);
            field121.addSubField(field121_2);
            field121.addSubField(field121_3);
            field121.addSubField(field121_4);
            field121.addSubField(field121_5);
            field121.encode();

            System.out.println(field121.toString());

            fields.add(field121);





            //报文鉴别码
            Field128 field128 = new Field128();
            field128.setData("MAC45678");
            System.out.println(field128.toString());

            fields.add(field128);

            int totalLen = 0;
            for (Field field : fields) {
                totalLen += field.getFieldLength();
            }
            System.out.println("实际总长度=" + totalLen);

            // 合并各个域的值
            fieldData.encode(fields);
            System.out.println(fieldData.toString());

            // 设置位图
            bitFieldMap.setFields(fields);
            System.out.println(bitFieldMap.showBitFieldMap());

            // 开始处理消息类型
            msgType.setMsgType("0420");

            // 设置消息头
            totalLen = MsgHeader.MSG_HEADER_SIZE + MessageType.MSG_TYPE_SIZE + bitFieldMap.getBitFieldMapLen() + fieldData.getFieldDataLen();
            msgHeader.encode(totalLen, "48020000", "B0210029", (byte) 0, "00000000", (byte) 0, "00000");

            // 消息编码,这一步非常重要，把msgHeader, msgType, bitFieldMap, fieldData合成msgContent
            request.setMsgHeader(msgHeader);
            request.setMsgType(msgType);
            request.setBitFieldMap(bitFieldMap);
            request.setFieldData(fieldData);

            //
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
