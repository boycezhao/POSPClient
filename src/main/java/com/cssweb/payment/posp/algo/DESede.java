package com.cssweb.payment.posp.algo;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by chenhf on 2014/11/11.
 */
public class DESede {



    private static final  String KEY_ALGORITHM = "DESede";
    private static final  String CIPHER_ALGORITHM = "DESede/ECB/NoPadding";


    /**
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public static byte[] initKey(int keyBitsLen) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        kg.init(keyBitsLen);

        SecretKey secretKey = kg.generateKey();

        return secretKey.getEncoded();
    }

    /**
     * @param key
     * @return
     * @throws java.security.InvalidKeyException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    private static Key toKey(byte[] key) {
        try {

            byte[] k = new byte[24];
            for (int i=0; i<k.length; i++)
                k[i] = (byte)0x00;
            System.arraycopy(key, 0, k, 0, key.length);

            DESedeKeySpec dks = new DESedeKeySpec(k);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM, "BC");


            SecretKey securekey = keyFactory.generateSecret(dks);

            return securekey;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 加密
     *
     * @param key
     * @param src
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] src) {
        try {
            Key k = toKey(key);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");

            cipher.init(Cipher.ENCRYPT_MODE, k);

            return cipher.doFinal(src);

        } catch (NoSuchPaddingException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (IllegalBlockSizeException e1) {
            e1.printStackTrace();
        } catch (BadPaddingException e1) {
            e1.printStackTrace();
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param key
     * @param src
     * @return public byte[] decrypt(byte[] key, byte[] src)
    {
    SecureRandom sr = new SecureRandom();

    try {
    Key k = toKey(key);


    Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");

    //cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
    cipher.init(Cipher.DECRYPT_MODE, k);

    return cipher.doFinal(src);

    } catch (NoSuchAlgorithmException e) {
    e.printStackTrace();
    } catch (InvalidKeyException e) {
    e.printStackTrace();
    }  catch (NoSuchPaddingException e) {
    e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
    e.printStackTrace();
    } catch (BadPaddingException e) {
    e.printStackTrace();
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    }

    return null;
    }
     */

    public static void main(String[] args)
    {
        byte[] key = BCD.A2B("12345678123456781234567812345678".getBytes());
        System.out.println("key长度=" + key.length);
        byte[] data = BCD.A2B("1234567812345678".getBytes());

        byte[] encryptData = DESede.encrypt(key, data);
        System.out.println("加密结果长度= " + encryptData.length);

        System.out.println("加密结果= " + Hex.encodeHexString(encryptData).toUpperCase());
    }
}
