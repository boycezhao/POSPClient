package com.cssweb.payment.posp.algo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by chenhf on 2014/11/11.
 */
public class DES {

    private static final  String KEY_ALGORITHM = "DES";
    private static final  String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    //private static final  String KEY_ALGORITHM = "DESede";
   // private static final  String CIPHER_ALGORITHM = "DESede/ECB/NoPadding";



    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        kg.init(64);

        SecretKey secretKey = kg.generateKey();

        return secretKey.getEncoded();
    }

    /**
     *
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static Key toKey(byte[] key) {


        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

            // DESedeKeySpec dks = new DESedeKeySpec(key);
            //SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM, "BC");



            SecretKey securekey = keyFactory.generateSecret(dks);

            return securekey;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 加密
     * @param key
     * @param src
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] src) {

        System.out.println("encrypt key len = " + key.length);

/*
public static String getEncString(String strMing, byte[] byteKey) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        byte[] buf = strMing.getBytes();
        int len = 8 - buf.length % 8;
        byteMing = new byte[buf.length + len];
        System.arraycopy(buf, 0, byteMing, 0, buf.length);
        byteMi = encrypt(byteMing, byteKey);
        return Base64.encode(byteMi);
    }
 */
        try {

            Key k = toKey(key);

            // CBC模式需要， ECB模式不需要IV
            byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
            IvParameterSpec zeroIv = new IvParameterSpec(iv);

            //Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            // 用密匙初始化Cipher对象
            //cipher.init(Cipher.ENCRYPT_MODE, k, zeroIv);
            cipher.init(Cipher.ENCRYPT_MODE, k);

            // 现在，获取数据并加密
            // 正式执行加密操作
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
        }

        return null;
    }

    /**
     *
     * @param key
     * @param src
     * @return
     */
    public byte[] decrypt(byte[] key, byte[] src)
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


}
