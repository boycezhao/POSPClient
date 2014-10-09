package com.cssweb.payment.pospclient.business;



import com.cssweb.payment.pospclient.network.CustomMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WorkerThread implements Runnable {
	private static final Logger logger =  LogManager.getLogger(WorkerThread.class.getName());
	private CustomMessage request;


    private BusiTestNetwork testNetwork = new BusiTestNetwork();
    private BusiGetBalance getBalance = new BusiGetBalance();
	

	public WorkerThread(CustomMessage req)
	{
		this.request = req;
	}



	public void run() {
        //CustomMessage response = null;

        // 解析消息内容
        if (!request.decode())
        {
            System.out.println("decodeMsgContent error");
        }

        String msgType = new String(request.getMsgType().getMsgType());

        logger.info("消息类型 = " + msgType);

        if (msgType.equals("0820"))
        {
            logger.info("收到echo应答");
            //testNetwork.process(request);
        }

        if (msgType == "0200")
        {
            //getBalance.process(request);
        }


        //req.getChannelHandlerContext().writeAndFlush(response);
	}
}
