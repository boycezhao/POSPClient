package com.cssweb.payment.posp.business;

import com.cssweb.payment.posp.common.Field;
import com.cssweb.payment.posp.network.CustomMessage;
import com.cssweb.payment.posp.network.FieldData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;

/**
 * Created by chenhf on 2014/10/15.
 */
public class BusiResetKey implements BusinessAction {

    private static final Logger logger = LogManager.getLogger(BusiResetKey.class.getName());

    @Override
    public CustomMessage process(CustomMessage request) {
        logger.info("==重置密钥:显示发卡方的应答==");

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
