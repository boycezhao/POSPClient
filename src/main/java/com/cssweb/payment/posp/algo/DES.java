package com.cssweb.payment.posp.algo;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by chenhf on 2014/11/11.
 */
public class DES {

    private static final  String KEY_ALGORITHM = "DES";

    private static final  String CIPHER_ALGORITHM = "DES/ECB/NoPadding";


    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        kg.init(56);

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
    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec dks = null;
        dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        SecretKey securekey = keyFactory.generateSecret(dks);

        return securekey;
    }

    /**
     * 加密
     * @param key
     * @param src
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] src) {


        try {

            Key k = toKey(key);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            // 用密匙初始化Cipher对象
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
        } catch (InvalidKeySpecException e1) {
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


            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            //cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            cipher.init(Cipher.DECRYPT_MODE, k);

            return cipher.doFinal(src);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
