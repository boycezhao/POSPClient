package com.cssweb.payment.posp.main;

import com.cssweb.payment.posp.client.POSPClient;
import com.cssweb.payment.posp.server.POSPServer;
import com.cssweb.payment.posp.business.WorkerThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chenhf on 2014/10/8.
 */
public class Main {

    public static void main(String[] args)
    {
        //
        WorkerThreadPool.getInstance().init(10, 10000);

        // 客户端线程
        POSPClient client = new POSPClient("127.0.0.1", 3500);
        Thread threadClient = new Thread(client);
        threadClient.start();

        // 创建服务线程
        POSPServer server = new POSPServer(2013, client);
        Thread thread = new Thread(server);
        thread.start();


        // 主线程
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("请输入命令（balance,consume, reverse, restkey, quit, exit）:");

                String cmd = console.readLine();



                if(cmd.equalsIgnoreCase("balance"))
                {

                    client.getBalance();

                }
                else if(cmd.equalsIgnoreCase("consume"))
                {

                    client.consume();

                }
                else if(cmd.equalsIgnoreCase("reverse"))
                {

                    client.consumeReverse();

                }
                
                else if(cmd.equalsIgnoreCase("resetkey"))
                {

                    client.resetKey();

                }
                else if(cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit"))
                {
                    server.stop();

                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
