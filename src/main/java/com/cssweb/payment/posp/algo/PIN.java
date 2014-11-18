package com.cssweb.payment.posp.algo;

import org.apache.commons.codec.binary.Hex;

/**
 * Created by chenhf on 2014/11/10.
 */
public class PIN {


    /**
     *
     * @param PIN
     * @return
     */
    public static byte[] getPINBlock(String PIN)
    {
        byte[] pin = PIN.getBytes();
        byte len = (byte) pin.length;

        byte[] bcd = BCD.A2B(pin);

        byte[] pinBlock = new byte[8];

        // 初始化或补数据
        for (int i=0; i<pinBlock.length; i++)
        {
            pinBlock[i] = (byte)0xFF;
        }

        //长度
        pinBlock[0] = len;

        //PIN
        System.arraycopy(bcd, 0, pinBlock, 1, bcd.length);

        return pinBlock;
    }

    /**
     *
     * @param PAN
     * @return
     */
    public static byte[] getPANBlock(String PAN)
    {
        int len = PAN.length();
        int begin = len - 12 - 1;
        String pan = PAN.substring(begin, begin + 12);
       // System.out.println("pan=" + pan);

        byte[] bcd = BCD.A2B(pan.getBytes());

        byte[] panBlock = new byte[8];
        for (int i=0; i<panBlock.length; i++)
        {
            panBlock[i] = 0x00;
        }

        System.arraycopy(bcd, 0, panBlock, 8-bcd.length, bcd.length);

        return panBlock;
    }

    /**
     * 采用双倍长密钥算法计算
     * @param PIK
     * @param PIN
     * @param PAN
     * @return
     */
    public static byte[] encryptPIN(byte[] PIK, byte[] PIN, byte[] PAN)
    {
        byte[] xor = XOR.bytesXOR(PAN, PIN);
        for (int i=0; i<xor.length; i++) {
            System.out.print("xor[" + i + "]=" + byte2Hex(xor[i]) + ",");
        }

        return null;
    }

    /**
     *
     * @param b
     * @return
     */
    public static String byte2Hex(byte b)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("0x");

        String hex = Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6).toUpperCase();
        sb.append(hex);

        return sb.toString();
    }

    public static void main(String args[])
    {
        byte[] pin = PIN.getPINBlock("123456");
        for (int i=0; i<pin.length; i++) {
            System.out.print("pin[" + i + "]=" + PIN.byte2Hex(pin[i]) + ",");
        }


        System.out.println();

       // byte[] pan = PIN.getPANBlock("123456789012345678");
        byte[] pan = PIN.getPANBlock("1234567890123456");
        for (int i=0; i<pan.length; i++) {
            System.out.print("pan[" + i + "]=" + PIN.byte2Hex(pan[i]) + ",");
        }

        System.out.println();

        byte[] PIK = null;
        byte[] encryptPIN = PIN.encryptPIN(PIK, pin, pan);
        for (int i=0; i<encryptPIN.length; i++) {
           // System.out.print("encryptPIN[" + i + "]=" + PIN.byte2Hex(encryptPIN[i]) + ",");
        }
    }
}
