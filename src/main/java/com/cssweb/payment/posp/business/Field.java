package com.cssweb.payment.posp.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhf on 2014/7/21.
 */
public class Field {
    private static final Logger logger = LogManager.getLogger(Field.class.getName());

    // 域描述
    protected String fieldName = "";

    // 域编号
    protected String fieldNo = "";

    // 数据类型
    protected String fieldType = "";
    public static final String FIELD_TYPE_BINARY_BIT = "b";
    public static final String FIELD_TYPE_BINARY_BYTE = "B";
    public static final String FIELD_TYPE_ANS = "ans";
    public static final String FIELD_TYPE_N = "n";
    public static final String FIELD_TYPE_Z = "Z";
    public static final String FIELD_TYPE_AN = "an";
    public static final String FIELD_TYPE_ANSB = "ansb";

    // 长度类型
    public static final int FIELD_LENGTH_TYPE_FIXED = 0; // 定长
    public static final int FIELD_LENGTH_TYPE_VAR2 = 2; // 变长2
    public static final int FIELD_LENGTH_TYPE_VAR3 = 3; // 变长3

    // 最大长度
    protected int maxFieldLength = 0;

    protected int fieldLengthType = FIELD_LENGTH_TYPE_FIXED;


    // 长度
    protected int fieldLength = 0;


    // 域值
    protected byte[] fieldValue = null;

    // 子域当前标志名称
    protected String tag = "";
    protected int valueLen = 0;
    protected String value = "";

    //如果是父域代表当前设置的是哪一个子域
    protected String currentTag = "";

    protected static final int FIELD_VALUE_TYPE_DEFAULT = 0;
    protected static final int FIELD_VALUE_TYPE_TL = 1;
    protected static final int FIELD_VALUE_TYPE_TLV = 2;
    protected int fieldValueType = FIELD_VALUE_TYPE_DEFAULT;

    // 相对于父域的开始位置
    protected int beginPos = 0;


    // 不采用getter/setter方式，可以
    protected Map<String, Field> fields = new HashMap<String, Field>();


    //以下定义暂时没使用
    protected boolean isSubField = false; // 是否是子域
    protected boolean hasSubField = false; // 是否有子域


    public Field()
    {
        /*
        if (fieldLengthType == FIELD_LENGTH_TYPE_FIXED)
        {
            fieldValue = new byte[fieldLength];
        }
        else
        {
            fieldValue = new byte[maxFieldLength];
        }
        */
    }



    public int getFieldValueType() {
        return fieldValueType;
    }
    public void setFieldValueType(int fieldValueType) {
        this.fieldValueType = fieldValueType;
    }

    public int getValueLen() {
        return valueLen;
    }
    public void setValueLen(int valueLen) {
        this.valueLen = valueLen;
    }


    public int getMaxFieldLength() {
        return maxFieldLength;
    }
    public void setMaxFieldLength(int maxFieldLength) {
        this.maxFieldLength = maxFieldLength;
    }

    public String getFieldType() {
        return fieldType;
    }
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldLengthType() {
        return fieldLengthType;
    }
    public void setFieldLengthType(int fieldLengthType) {
        this.fieldLengthType = fieldLengthType;
    }

    public int getFieldLength() {
        return fieldLength;
    }
    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldNo() {
        return fieldNo;
    }
    public void setFieldNo(String fieldNo) {
        this.fieldNo = fieldNo;
    }


    public String getCurrentTag() {
        return currentTag;
    }
    public void setCurrentTag(String currentTag) {
        this.currentTag = currentTag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag()
    {
        return tag;
    }


    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public int getBeginPos() {
        return beginPos;
    }
    public void setBeginPos(int beginPos) {
        this.beginPos = beginPos;
    }

    public Map<String, Field> getFields() {
        return fields;
    }
    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    /**
     *
     * @param data
     */
    public void setFieldValue(byte[] data)
    {
        if (fieldValueType == FIELD_VALUE_TYPE_DEFAULT)
        {
            this.fieldValue = data;
        }
        else if(fieldValueType == FIELD_VALUE_TYPE_TL)
        {
            int tagLen = tag.getBytes().length;

            fieldValue = new byte[tagLen + valueLen]; //tag所占字节长度+数据长度

            System.arraycopy(tag.getBytes(), 0, this.fieldValue, 0, tagLen);
            System.arraycopy(data, 0, this.fieldValue, tagLen, data.length);
        }
        else if(fieldValueType == FIELD_VALUE_TYPE_TLV)
        {

        }
        else
        {
            //throw exception
        }
    }

    /**
     *
     * @param data
     */
    public void setFieldValue(String data)
    {
        setFieldValue(data.getBytes());
    }



    public byte[] getFieldValue()
    {
        if (fieldLengthType == FIELD_LENGTH_TYPE_FIXED)
        {
            return fieldValue;
        }
        else if (fieldLengthType == FIELD_LENGTH_TYPE_VAR2)
        {
            // 真实域内容长度
            int actualLen = fieldValue.length;
            String s = String.valueOf(actualLen);
            if (actualLen < 10)
            {
                // 长度左边补0
                s = "0" + s;
            }


            // 长度转成字符串，转成字节数组
            byte[] varLen = s.getBytes(); // 例如："09"



            byte[] val = new byte[2 + actualLen];
            System.arraycopy(varLen, 0, val, 0, varLen.length);
            System.arraycopy(fieldValue, 0, val, varLen.length, fieldValue.length);

            return val;
        }
        /*
        else if (fieldLengthType == FIELD_LENGTH_TYPE_VAR3)
        {

            // 真实域内容长度
            int actualLen = fieldValue.length;
            //if (actualLen > 2)
            {
                //throw;
            }

            // 长度转成字符串，转成字节数组
            byte[] varLen = String.valueOf(actualLen).getBytes();

            // 域可变内容
            byte[] actual = fieldValue.getBytes();

            byte[] val = new byte[3 + actualLen];
            System.arraycopy(varLen, 0, val, 0, varLen.length);
            System.arraycopy(actual, 0, val, varLen.length, actual.length);

            return val;
        }
        */
        else
        {
            return null;
        }
    }

    /**
     * 验证数据格式
     * @return
     */
    public boolean validate()
    {
        // 用正则表达式验证数据格式
        // 返回RejectCode
        return true;
    }




    /**
     *
     * @return
     */
    public String toString()
    {

        String s = fieldNo + "[" + fieldName + "]:" + new String(fieldValue);

        return s;
    }

    /**
     *
     * @param field
     */
    public void addField(Field field)
    {
        System.arraycopy(field.getFieldValue(), 0, fieldValue, field.getBeginPos(), field.getFieldLength());

        fields.put(field.getFieldName(), field);
    }
}
