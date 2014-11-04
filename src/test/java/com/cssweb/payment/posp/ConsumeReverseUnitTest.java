package com.cssweb.payment.posp;

import com.cssweb.payment.posp.client.TestConsumeReverse;
import com.cssweb.payment.posp.client.TestGetBalance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhf on 2014/10/28.
 */
public class ConsumeReverseUnitTest {
    private static final Logger logger = LogManager.getLogger(ConsumeReverseUnitTest.class.getName());

    public static void main(String[] args)
    {
        TestConsumeReverse test = new TestConsumeReverse();
        test.getRequest();


    }
}
