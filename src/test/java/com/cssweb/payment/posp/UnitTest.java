package com.cssweb.payment.posp;

import com.cssweb.payment.posp.client.TestGetBalance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhf on 2014/10/28.
 */
public class UnitTest {
    private static final Logger logger = LogManager.getLogger(UnitTest.class.getName());

    public static void main(String[] args)
    {
        TestGetBalance testGetBalance = new TestGetBalance();
        testGetBalance.getRequest();


    }
}
