package com.cssweb.payment.posp.business;

/**
 * Created by chenhf on 2014/10/20.
 */
public class Field121_5 extends Field{

    private Field121_5_FD fd;
    private Field121_5_ID id;

    public Field121_5_FD getFd() {
        return fd;
    }

    public Field121_5_ID getId() {
        return id;
    }


    public void setFd(Field121_5_FD fd) {
        this.fd = fd;

        tag = fd.getTag();

        System.arraycopy(fd.getFieldValue(), 0,  fieldValue, 0, fd.getFieldLength());
    }

    public void setId(Field121_5_ID id) {
        this.id = id;

        tag = id.getTag();

        System.arraycopy(id.getFieldValue(), 0,  fieldValue, 0, id.getFieldLength());
    }

    public Field121_5()
    {
        fieldName = "转入和转出方标识代码/手续费信息";
        fieldNo = "121.5";

        fieldType = FIELD_TYPE_ANS;

        fieldLengthType = FIELD_LENGTH_TYPE_FIXED;
        fieldLength = 38;

        isSubField = true;
        fieldValue = new byte[fieldLength];
    }
}
