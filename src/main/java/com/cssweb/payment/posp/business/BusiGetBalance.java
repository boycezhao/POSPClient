package com.cssweb.payment.posp.business;


import com.cssweb.payment.posp.common.Field;
import com.cssweb.payment.posp.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;

/**
 * Created by chenhf on 2014/8/25.
 */
public class BusiGetBalance implements BusinessAction {

    private static final Logger logger = LogManager.getLogger(BusiGetBalance.class.getName());



    @Override
    public CustomMessage process(CustomMessage request) {
        logger.info("==余额查询:显示发卡方的应答==");

        FieldData fieldData = request.getFieldData();
        logger.info(fieldData.toString());

        LinkedHashMap<Integer, Field> fieldMap  = fieldData.getFieldMap();
        for (Field field : fieldMap.values())
        {
            logger.info(field.toString());
        }

        return null;
    }
}
