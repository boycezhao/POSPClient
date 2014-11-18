package com.cssweb.payment.posp.algo;

import org.apache.commons.codec.binary.Hex;

/**
 * Created by chenhf on 2014/11/18.
 */
public class XOR {

    /**
     * 单字节
     * @param src1
     * @param src2
     * @return
     */
    private static byte byteXOR(byte src1, byte src2) {
        return (byte) ((src1 & 0xFF) ^ (src2 & 0xFF));
    }

    /**
     * @param src1
     * @param src2
     * @return
     */
    public static byte[] bytesXOR(byte[] src1, byte[] src2) {
        int length = src1.length;

        if (length != src2.length) {
            return null;
        }

        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            result[i] = byteXOR(src1[i], src2[i]);
        }

        return result;
    }

    public static void main(String args[])
    {
        byte[] src1 = BCD.A2B("1234567887654321".getBytes());
        byte[] src2 = BCD.A2B("1234567812345678".getBytes());

        byte[] result = XOR.bytesXOR(src1, src2);
        System.out.println("异或结果长度=" + result.length);
/*
        for (int i=0; i<result.length; i++)
        {
            System.out.println("result[" + i +"]=" + result[i]);
        }
*/

        System.out.println("xor = " + Hex.encodeHexString(result));
    }
}
