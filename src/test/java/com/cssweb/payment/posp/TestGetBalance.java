package com.cssweb.payment.posp;

import com.cssweb.payment.posp.business.*;
import com.cssweb.payment.posp.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhf on 2014/10/28.
 */
public class TestGetBalance {
    private static final Logger logger = LogManager.getLogger(TestGetBalance.class.getName());

    public static void main(String[] args)
    {
        BusiGetBalance test = new BusiGetBalance();

        CustomMessage request = new CustomMessage();
        test.process(request);


    }
}
