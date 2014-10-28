package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/10/15.
 */
public class Field121 extends Field {

    private Field121_1 f1;
    private Field121_2 f2;
    private Field121_3 f3;
    private Field121_4 f4;
    private Field121_5 f5;

    public Field121_1 getF1() {
        return f1;
    }
    public Field121_2 getF2() {
        return f2;
    }
    public Field121_3 getF3() {
        return f3;
    }
    public Field121_4 getF4() {
        return f4;
    }
    public Field121_5 getF5() {
        return f5;
    }


    public void setF1(Field121_1 f1) {
        this.f1 = f1;
        System.arraycopy(f1.getFieldValue(), 0,  fieldValue, 0, f1.getFieldLength());
    }
    public void setF2(Field121_2 f2) {
        this.f2 = f2;
        System.arraycopy(f2.getFieldValue(), 0,  fieldValue, 1, f2.getFieldLength());
    }
    public void setF3(Field121_3 f3) {
        this.f3 = f3;
        System.arraycopy(f3.getFieldValue(), 0,  fieldValue, 2, f3.getFieldLength());
    }
    public void setF4(Field121_4 f4) {
        this.f4 = f4;
        System.arraycopy(f4.getFieldValue(), 0,  fieldValue, 3, f4.getFieldLength());
    }
    public void setF5(Field121_5 f5) {
        this.f5 = f5;
        System.arraycopy(f5.getFieldValue(), 0,  fieldValue, 43, f5.getFieldLength());
    }

    public Field121()
    {
        fieldName = "预付卡发卡机构保留";
        fieldNo = "121";

        fieldType = FIELD_TYPE_ANS;

        fieldLengthType = FIELD_LENGTH_TYPE_VAR3;
        maxFieldLength = 100;

        isSubField = true;

        fieldValue = new byte[maxFieldLength];
    }
}
