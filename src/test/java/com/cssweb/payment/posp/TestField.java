package com.cssweb.payment.posp;

import com.cssweb.payment.posp.business.*;

/**
 * Created by chenhf on 2014/10/28.
 */
public class TestField {
    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();

        //预付卡发卡机构保留
        Field121 field121 = new Field121();

        //应答/应答原因码
        Field121_1 field121_1 = new Field121_1();
        field121_1.setFieldValue(Field121_1.RC_ISSUER_RESPONSE);
        System.out.println(field121_1.toString());


        //单/双或双/单转换码
        Field121_2 field121_2 = new Field121_2();
        field121_2.setFieldValue(Field121_2.CC_UNKNOWN);
        System.out.println(field121_2.toString());

        //卡性质
        Field121_3 field121_3 = new Field121_3();
        field121_3.setFieldValue(Field121_3.CARD_TYPE_CUP_DEBIT);
        System.out.println(field121_3.toString());

        //预付卡发卡机构保留
        Field121_4 field121_4 = new Field121_4();
        sb.delete(0, sb.length());
        for (int i=0; i < field121_4.getFieldLength(); i++) {
            sb.append('0');
        }
        field121_4.setFieldValue(sb.toString().getBytes());
        System.out.println(field121_4.toString());




        Field121_5_ID field121_5_id = new Field121_5_ID();
        sb.delete(0, sb.length());
        for (int i=0; i < field121_5_id.getValueLen(); i++) {
            sb.append('1');
        }
        field121_5_id.setFieldValue(sb.toString().getBytes());
        System.out.println(field121_5_id.toString());

        //转入和转出方标识代码/手续费信息
        Field121_5 field121_5 = new Field121_5();
        field121_5.addField(field121_5_id);
        //field121_5.addField(field121_5_id);
        System.out.println(field121_5.toString());


        field121.addField(field121_1);
        field121.addField(field121_2);
        field121.addField(field121_3);
        field121.addField(field121_4);
        field121.addField(field121_5);


       System.out.println(field121.toString());

    }
}
