package com.cssweb.payment.posp.security.HSM;

import com.cssweb.payment.posp.security.SecurityInterface;

/**
 * Created by chenhf on 2014/11/19.
 */
public class HSMImpl implements SecurityInterface {


    @Override
    public byte[] calcMAC(byte[] MAK, byte[] mab) {
        return new byte[0];
    }

    @Override
    public byte[] calcPIN(byte[] PIK, String pin, String pan) {
        return new byte[0];
    }
}
