package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/10/13.
 */
public class Field32 extends Field{

    public Field32()
    {
        fieldName = "受理机构标识码";
        fieldNo = "32";

        fieldType = FIELD_TYPE_N;

        fieldLengthType = FIELD_LENGTH_TYPE_VAR2;
        maxFieldLength = 2+11;
       // fieldValue = new byte[maxFieldLength];
    }
}
