package com.cssweb.payment.posp.algo;

import com.cssweb.payment.posp.common.Field;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Created by chenhf on 2014/11/10.
 */
public class MAC {

    public static final String MAK = "1234567812345678";

    /**
     * 第一步：选择参与计算的域
     * 第二步：整理数据
     * 第三步：划分成64bit的MAB(Message Authentication Block);
     *
     */

    /**
     *
     * @param src1
     * @param src2
     * @return
     */
    private byte byteXOR(byte src1, byte src2)
    {
        return (byte) ((src1 & 0xFF) ^ (src2 & 0xFF));
    }

    /**
     *
     * @param src1
     * @param src2
     * @return
     */
    public byte[] bytesXOR(byte[] src1, byte[] src2)
    {
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

    /**
     *
     * @param MAK
     * @param src
     * @return
     */
    public byte[] calcMAC(byte[] MAK, byte[] src)
    {
        // 补足数据，8的倍数
        int length = src.length;

        int x = length % 8;
        int addLen = 0;
        if (x != 0) {
            addLen = 8 - length % 8;
        }

        byte[] data = new byte[length + addLen];
        System.arraycopy(src, 0, data, 0, length);


        // 获取第一块MAB
        int pos = 0;
        byte[] oper1 = new byte[8];
        System.arraycopy(data, pos, oper1, 0, 8);
        pos += 8;

        for (int i = 1; i < data.length / 8; i++) {

            // 获取后面MAB Block
            byte[] oper2 = new byte[8];
            System.arraycopy(data, pos, oper2, 0, 8);

            // 存放异或结果
            byte[] t = bytesXOR(oper1, oper2);
            oper1 = t;

            pos += 8;
        }

        // 将异或运算后的最后8个字节（RESULT BLOCK）转换成16个HEXDECIMAL：
        byte[] resultBlock = bytesToHexString(oper1).getBytes();

        // 取前8个字节用mkey1，DES加密
        byte[] front8 = new byte[8];
        System.arraycopy(resultBlock, 0, front8, 0, 8);
        byte[] desfront8 = DES.encrypt(MAK, front8);

        // 取后面8个字节
        byte[] behind8 = new byte[8];
        System.arraycopy(resultBlock, 8, behind8, 0, 8);

        // 将加密后的结果与后8 个字节异或：
        byte[] resultXOR = bytesXOR(desfront8, behind8);

        // 用异或的结果TEMP BLOCK 再进行一次单倍长密钥算法运算
        System.out.println("mak =  " + MAK.length);
        System.out.println("xor = " + resultXOR.length);

        byte[] buff = DES.encrypt(MAK, resultXOR);

        // 将运算后的结果（ENC BLOCK2）转换成16 个HEXDECIMAL asc
        // 取8个长度字节
        byte[] retBuf = new byte[8];

        String finalResult = bytesToHexString(buff);
        System.out.println("finalResult = " + finalResult);

        System.arraycopy(finalResult.getBytes(), 0, retBuf, 0, 8);

        return retBuf;
    }

    /**
     *
     * @param field
     * @return
     */
    public byte[] addField(Field field)
    {
        return null;
    }
    /**
     *
     * @param data
     * @return
     */
    private String bytesToHexString(byte[] data)
    {
        StringBuffer sb = new StringBuffer();

        String sTemp;
        for (int i = 0; i < data.length; i++) {
            sTemp = Integer.toHexString(0xFF & data[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args)
    {


        MAC mac = new MAC();

        // 测试XOR
        /*
        byte[] src1 = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        byte[] src2 = {8, 7, 6, 5, 4, 3, 2, 1, 8, 7, 6, 5, 4, 3, 2, 1};
        byte[] result = mac.bytesXOR(src1, src2);
        for (int i =0; i<result.length; i++)
        {

            System.out.println(result[i]);
        }
        */

        // 测试DES加密
            //byte[] key  = DES.initKey();
            //System.out.println("key len = " + key.length);

            byte[] result = DES.encrypt("12345678".getBytes(), "1234567812345678".getBytes());
            System.out.println("des result=" + Hex.encodeHexString(result).toUpperCase());







// 计算ANSI-X9.9-MAC


        // key必须是24位， 后面8位无效，
        System.out.println("MAC =" + new String( mac.calcMAC("12345678".getBytes(), "1234567812345678".getBytes()) ) );

    }
}
