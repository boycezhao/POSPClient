package com.cssweb.payment.posp;

import com.cssweb.payment.posp.client.TestConsume;
import com.cssweb.payment.posp.client.TestGetBalance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhf on 2014/10/28.
 */
public class ConsumeUnitTest {
    private static final Logger logger = LogManager.getLogger(ConsumeUnitTest.class.getName());

    public static void main(String[] args)
    {
        TestConsume test = new TestConsume();
        test.getRequest();


    }
}
