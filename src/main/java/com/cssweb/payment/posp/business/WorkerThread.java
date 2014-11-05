package com.cssweb.payment.posp.business;



import com.cssweb.payment.posp.client.POSPClient;
import com.cssweb.payment.posp.network.CustomMessage;
import com.cssweb.payment.posp.network.FieldData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WorkerThread implements Runnable {
	private static final Logger logger =  LogManager.getLogger(WorkerThread.class.getName());
    private POSPClient client;
    private CustomMessage request;

    public WorkerThread(CustomMessage req, POSPClient client)
    {
        this.request = req;
        this.client = client;
    }



	public void run() {
        CustomMessage response = null;

        // 解析消息内容
        if (!request.decode())
        {
            logger.error("解码消息失败");
        }

        String msgType = new String(request.getMsgType().getMsgType());

        logger.info("消息类型 = " + msgType);



        if (msgType.equals("0210"))
        {
            FieldData reqFieldData = request.getFieldData();
            Field3 reqField3 = (Field3) reqFieldData.getField(3);
            String f3data = new String(reqField3.getData());

            if (f3data.equalsIgnoreCase("30x000"))
            {
                // 余额查询
                BusiGetBalance getBalance = new BusiGetBalance();
                response = getBalance.process(request);
                //client.sendResponse(response);
            }
            else if(f3data.equalsIgnoreCase("00x000"))
            {
                // 消费
                BusiConsume consume = new BusiConsume();
                response = consume.process(request);
            }
            else if(f3data.equalsIgnoreCase("20x000"))
            {
                // 消费撤销
                BusiConsumeCancel consumeCancel = new BusiConsumeCancel();
                response = consumeCancel.process(request);
            }
            else
            {

            }
        }
        else if(msgType.equals("0430"))
        {
            FieldData reqFieldData = request.getFieldData();
            Field3 reqField3 = (Field3) reqFieldData.getField(3);
            String f3data = new String(reqField3.getData());

            if (f3data.equalsIgnoreCase("00x000")) {

                // 冲正
                BusiConsumeReverse consumeReverse = new BusiConsumeReverse();
                consumeReverse.process(request);
            }
            else if (f3data.equalsIgnoreCase("20x000"))
            {
                // 消费冲正撤消
                BusiConsumeReverseCancel consumeReverseCancel = new BusiConsumeReverseCancel();
                response = consumeReverseCancel.process(request);
            }
        }
        else if(msgType.equals("0230"))
        {
            // 退货
            BusiSalesReturn salesReturn = new BusiSalesReturn();
            salesReturn.process(request);
        }
        else if(msgType.equals("0820"))
        {

                BusiApplyKey applyKey = new BusiApplyKey();
                response = applyKey.process(request);

        }
        else if(msgType.equals("0810"))
        {
            // 处理来自POSPClient的请求
            BusiResetKey resetKey = new BusiResetKey();
            response = resetKey.process(request);

        }
        else
        {

        }
	}
}
