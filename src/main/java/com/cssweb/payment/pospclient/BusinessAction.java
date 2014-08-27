package com.cssweb.payment.pospclient;


import com.cssweb.payment.pospclient.network.CustomMessage;

/**
 * Created by chenhf on 2014/8/25.
 */
public interface BusinessAction {

    public void process(CustomMessage customMessage);
}
