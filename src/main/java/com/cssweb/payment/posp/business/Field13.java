package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/10/15.
 */
public class Field13 extends Field{
    public Field13()
    {
        fieldName = "受卡方所在地日期";
        fieldNo = "13";

        fieldType = FIELD_TYPE_N;

        fieldLengthType = FIELD_LENGTH_TYPE_FIXED;
        fieldLength = 4;
    }
}
