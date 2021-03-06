package com.cssweb.payment.posp.server;


import com.cssweb.payment.posp.business.WorkerThread;
import com.cssweb.payment.posp.business.WorkerThreadPool;
import com.cssweb.payment.posp.client.POSPClient;
import com.cssweb.payment.posp.network.CustomMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class NettyServerHandler extends
		SimpleChannelInboundHandler<CustomMessage> {

	private static final Logger logger = LogManager
			.getLogger(NettyServerHandler.class.getName());

    private POSPClient client;


    public NettyServerHandler(POSPClient client)
    {
        this.client = client;
    }

	@Override
	public void channelRead0(ChannelHandlerContext ctx, CustomMessage request)
			throws Exception {
		logger.info("channelRead0");

		request.setChannelHandlerContext(ctx);

		WorkerThread reqTask = new WorkerThread(request,client);
		WorkerThreadPool.getInstance().execute(reqTask);

		// ctx.writeAndFlush(request);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelInactive");
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("exceptionCaught", cause);
		ctx.close();
	}
}
