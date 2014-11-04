package com.cssweb.payment.posp;

import com.cssweb.payment.posp.client.TestGetBalance;
import com.cssweb.payment.posp.client.TestResetKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhf on 2014/10/28.
 */
public class ResetKeyUnitTest {
    private static final Logger logger = LogManager.getLogger(ResetKeyUnitTest.class.getName());

    public static void main(String[] args)
    {
        TestResetKey test = new TestResetKey();
        test.getRequest();


    }
}
