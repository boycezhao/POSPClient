package com.cssweb.payment.posp.business;


import com.cssweb.payment.posp.network.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhf on 2014/8/25.
 */
public class BusiGetBalance implements BusinessAction {

    @Override
    public CustomMessage process(CustomMessage request) {

        Date now = new Date();

        String AC_ID = "00000000000";
        String SW_ID = "11111111111";
        String IS_ID = "22222222222";

        String referNo = "181030414357";
        String terminalNo = "63856007";

        CustomMessage response = new CustomMessage();

        MsgHeader msgHeader = new MsgHeader();
        MessageType msgType = new MessageType();
        BitFieldMap bitFieldMap = new BitFieldMap();
        FieldData fieldData = new FieldData();

        List<Field> fields = new ArrayList<Field>();

        // 主账号
        Field2 field2 = new Field2();
        field2.setFieldValue("6226090217946181");
        fields.add(field2);

        //交易处理码
        Field3 field3 = new Field3();
        //301000
        field3.setTradeType(Field3.TRD_TYPE_QUERY_SERVICE);
        field3.setFrom(Field3.FROM_DEPOSIT + Field3.FROM_DEFAULT);
        field3.setTo(Field3.TO_DEFAULT + Field3.TO_DEFAULT);
        fields.add(field3);

        // 交易传输时间
        Field7 field7 = new Field7();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String tranTime = sdf.format(now);
        field7.setFieldValue(tranTime);
        fields.add(field7);


        //系统跟踪号
        Field11 field11 = new Field11();
        String traceNo = "";
        Random random = new Random();
        for (int i=0; i<6; i++) {
            traceNo += random.nextInt(10);
        }
        field11.setFieldValue(traceNo);
        fields.add(field11);

        //受卡方所在地时间
        Field12 field12 = new Field12();
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        String t = time.format(now);
        field12.setFieldValue(t);
        fields.add(field12);

        //受卡方所在地日期
        Field13 field13 = new Field13();
        SimpleDateFormat date = new SimpleDateFormat("MMdd");
        String d = date.format(now);
        field13.setFieldValue(d);
        fields.add(field13);

        // 卡有效期
        Field14 field14 = new Field14();
        field14.setFieldValue("1612"); // 2016年12月份
        fields.add(field14);

        // 清算日期
        Field15 field15 = new Field15();
        //SimpleDateFormat date = new SimpleDateFormat("MMdd");
        //String d = date.format(now);
        field13.setFieldValue(d);
        fields.add(field15);

        // 商户类型
        //取值请参见GB/T 20548-2006《金融零售业务 商户类别代码》
        Field18 field18 = new Field18();
        field18.setFieldValue("1111");
        fields.add(field18);

        //商户国家代码
        //参见世界各国和地区名称代码（GB/T 2659-94）
        Field19 field19 = new Field19();
        field19.setFieldValue("156");

        //服务点输入方式码
        Field22 field22 = new Field22();
        field22.setPAN(Field22.PAN_IC);
        field22.setPIN(Field22.PIN_INCLUDE);
        fields.add(field22);

        //服务点条件码
        Field25 field25 = new Field25();
        field25.setFieldValue(Field25.COMMIT);
        fields.add(field25);

        //服务点PIN获取码
        Field26 field26 = new Field26();
        field26.setFieldValue("06"); // 6位长度密码
        fields.add(field26);

        //受理机构标识码
        Field32 field32 = new Field32();
        field32.setFieldValue(AC_ID);
        fields.add(field32);

        //发送机构标识码
        Field33 field33 = new Field33();
        field33.setFieldValue("11111111111");
        fields.add(field33);


        //第二磁道数据
        Field35 field35 = new Field35();
        //第三磁道数据
        Field36 field36 = new Field36();

        //检索参考号
        Field37 field37 = new Field37();
        field37.setFieldValue(referNo);
        fields.add(field37);

        //应答码
        Field39 field39 = new Field39();
        //field39.setFieldValue();

        //受卡机终端标识码
        Field41 field41 = new Field41();
        field41.setFieldValue(terminalNo);
        fields.add(field41);

        //受卡方标识码
        Field42 field42 = new Field42();
        field42.setFieldValue("AC ID");
        fields.add(field42);

        //受卡方名称地址
        Field43 field43 = new Field43();
        field43.setFieldValue("地址");
        fields.add(field43);



        //第一磁道数据
        Field45 field45 = new Field45();

        //附加数据－私有
        Field48 field48 = new Field48();

        Field48_AA field48_aa = new Field48_AA();


        fields.add(field48);

        //交易货币代码
        //参见ISO 4217标准
        Field49 field49 = new Field49();
        field49.setFieldValue("CNY");
        fields.add(field49);

        //个人标识码数据，存放加密后的PIN
        Field52 field52 = new Field52();
        field52.setFieldValue("enc pin");
        fields.add(field52);

        //安全控制信息
        Field53 field53 = new Field53();
        field53.setPinFormat(Field53.PIN_FORMAT_PAN);
        field53.setEncAlgo(Field53.ENCRYPTION_METHOD_DOUBLE);
        fields.add(field53);

        //实际余额
        Field54 field54 = new Field54();

        //附加交易信息
        Field57 field57 = new Field57();

        //自定义域
        Field60 field60 = new Field60();
        fields.add(field60);

        //持卡人身份认证信息
        Field61 field61 = new Field61();
        fields.add(field61);

        //接收机构标识码
        Field100 field100 = new Field100();
        field100.setFieldValue(SW_ID);
        fields.add(field100);

        //预付卡发卡机构保留
        Field121 field121 = new Field121();

        //应答/应答原因码
        Field121_1 field121_1 = new Field121_1();
        field121_1.setFieldValue(Field121_1.RC_ISSUER_RESPONSE);
        field121.setF1(field121_1);

        //单/双或双/单转换码
        Field121_2 field121_2 = new Field121_2();
        field121_2.setFieldValue(Field121_2.CC_UNKNOWN);
        field121.setF2(field121_2);

        //卡性质
        Field121_3 field121_3 = new Field121_3();
        field121_3.setFieldValue(Field121_3.CARD_TYPE_CUP_DEBIT);
        field121.setF3(field121_3);

        //预付卡发卡机构保留
        Field121_4 field121_4 = new Field121_4();
        field121_4.setFieldValue('0', field121_4.getFieldLength());
        field121.setF4(field121_4);




        Field121_5_ID field121_5_id = new Field121_5_ID();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i < field121_5_id.getFieldLength()-2; i++) {
            sb.append('0');
        }
        field121_5_id.setFieldValue(sb.toString().getBytes());

        //转入和转出方标识代码/手续费信息
        Field121_5 field121_5 = new Field121_5();
        field121_5.setId(field121_5_id);

        field121.setF5(field121_5);

        fields.add(field121);

        //受理方保留
        Field122 field122 = new Field122();
        Field122_1 field122_1 = new Field122_1();
        Field122_2 field122_2 = new Field122_2();


        //发卡方保留
        Field123 field123 = new Field123();

        //报文鉴别码
        Field128 field128 = new Field128();
        field128.setFieldValue("MAC");
        fields.add(field128);

        return null;
    }
}
