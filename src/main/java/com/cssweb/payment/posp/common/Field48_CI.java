package com.cssweb.payment.posp.common;

/**
 * Created by chenhf on 2014/10/22.
 */
public class Field48_CI extends Field {


    public Field48_CI()
    {
        fieldName = "持卡人身份信息";
        fieldNo = "48.CI";

        fieldType = FIELD_TYPE_ANS;

        fieldValueType = FIELD_VALUE_TYPE_TV;
        tag = "CI";
        dataLen = 20+2+20+14+42;
    }

}
