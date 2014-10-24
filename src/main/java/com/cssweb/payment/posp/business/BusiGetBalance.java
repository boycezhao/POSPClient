package com.cssweb.payment.posp.business;


import com.cssweb.payment.posp.network.CustomMessage;

/**
 * Created by chenhf on 2014/8/25.
 */
public class BusiGetBalance implements BusinessAction {

    @Override
    public CustomMessage process(CustomMessage request) {

        Field2 field2 = new Field2();
        field2.setFieldValue("6226090217946181");
        Field3 field3 = new Field3();
        Field7 field7 = new Field7();

        Field11 field11 = new Field11();
        Field12 field12 = new Field12();
        Field13 field13 = new Field13();
        Field14 field14 = new Field14();
        Field15 field15 = new Field15();
        Field18 field18 = new Field18();
        Field19 field19 = new Field19();

        Field22 field22 = new Field22();
        Field25 field25 = new Field25();
        Field26 field26 = new Field26();

        Field32 field32 = new Field32();
        Field33 field33 = new Field33();
        Field35 field35 = new Field35();
        Field36 field36 = new Field36();
        Field37 field37 = new Field37();
        Field39 field39 = new Field39();

        Field41 field41 = new Field41();
        Field42 field42 = new Field42();
        Field43 field43 = new Field43();
        Field45 field45 = new Field45();
        Field48 field48 = new Field48();
        Field49 field49 = new Field49();

        Field52 field52 = new Field52();
        Field53 field53 = new Field53();
        Field54 field54 = new Field54();
        Field57 field157 = new Field57();

        Field60 field60 = new Field60();
        Field61 field61 = new Field61();

        Field100 field100 = new Field100();
        Field121 field121 = new Field121();
        Field121_1 field121_1 = new Field121_1();
        Field121_2 field121_2 = new Field121_2();
        Field121_3 field121_3 = new Field121_3();
        Field121_4 field121_4 = new Field121_4();
        Field121_5 field121_5 = new Field121_5();
        field121.addField(field121_1);
        field121.addField(field121_2);
        field121.addField(field121_3);
        field121.addField(field121_4);
        field121.addField(field121_5);

        Field122 field122 = new Field122();
        Field122_1 field122_1 = new Field122_1();
        Field122_2 field122_2 = new Field122_2();
        field122.addField(field122_1);
        field122.addField(field122_2);

        Field123 field123 = new Field123();
        Field128 field128 = new Field128();

        return null;
    }
}
