package com.cssweb.payment.pospclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenhf on 2014/8/20.
 */
public class BitFieldMap {

    //private HashMap<Integer, Field> bm;
    private byte[] mainBitmap = new byte[16];

    private List<Field> fields = new ArrayList<Field>();

    public void addField(Field field)
    {
        fields.add(field);
    }

    public byte[] getData()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // ByteBuffer

        //StringBuffer sb = new StringBuffer();
        for (Field field : fields)
        {
            byte[] fieldValue = field.getFieldValue();
            try {
                baos.write(fieldValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return baos.toByteArray();
    }

}
