package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/10/15.
 */
public class Field15 extends Field {

    public Field15()
    {
        fieldName = "清算日期";
        fieldNo = "15";

        fieldType = FIELD_TYPE_N;

        fieldLengthType = FIELD_LENGTH_TYPE_FIXED;
        fieldLength = 4;
    }
}
