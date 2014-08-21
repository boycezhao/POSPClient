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
    private byte[] mainBitmap = new byte[8];
    private byte[] extBitmap = new byte[8];

    private List<Field> fields = new ArrayList<Field>();

    private char[] array = new char[128];


    public BitFieldMap()
    {
        // 初始化
        for (int i=0; i<array.length; i++)
        {
            array[i] = '0';
        }
    }

    public void addField(Field field)
    {
        fields.add(field);

        int fieldNo = field.getFieldNo();

        array[fieldNo - 1] = '1'; // 置有效位

        if (fieldNo > 64)
            array[0] = '1'; // 说明位图2有效
    }

    public byte[] getMainBitFieldMap()
    {
        String binaryStr = "";
        for (int i=0, j=1; i<64; i++, j++)
        {
            binaryStr += array[i];

            // 每8个字节执行一次
            if (j == 8)
            {
                System.out.println("i=" +i + ",j=" + j + ", binaryStr=" + binaryStr);

                byte b = BitUtil.binaryStrToByte(binaryStr);

                int index = i/8;
                mainBitmap[index] = b;


                binaryStr = "";
                j = 0;
            }
        }

        return mainBitmap;
    }

    public byte[] getExtBitFieldMap()
    {
        String binaryStr = "";
        for (int i=64, j=1; i<128; i++, j++)
        {
            binaryStr += array[i];

            // 每8个字节执行一次
            if (j == 8)
            {
                System.out.println("i=" +i + ",j=" + j + ", binaryStr=" + binaryStr);

                byte b = BitUtil.binaryStrToByte(binaryStr);

                int index = i/8;
                extBitmap[index-8] = b;

                binaryStr = "";
                j = 0;
            }
        }

        return extBitmap;
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
