package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/8/20.
 */
public class Field33 extends Field {



  public Field33()
  {
      fieldName = "发送机构标识码";

      fieldNo = "33";

      fieldType = FIELD_TYPE_N;

      fieldValueType = FIELD_VALUE_TYPE_LLV;
      maxFieldLength = 11;
     // fieldValue = new byte[maxFieldLength];
  }
}
