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

        // 创建服务线程
        POSPServer server = new POSPServer(2013);
        Thread thread = new Thread(server);
        thread.start();


        // 主线程
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("请输入命令（balance,consume, reverse, applykey, restkey, quit, exit）:");

                String cmd = console.readLine();



                if(cmd.equalsIgnoreCase("balance"))
                {
                    POSPClient client = new POSPClient();
                    client.connect("127.0.0.1", 3500);
                    client.getBalance();
                    client.close();
                }
                else if(cmd.equalsIgnoreCase("consume"))
                {
                    POSPClient client = new POSPClient();
                    client.connect("127.0.0.1", 3500);
                    client.consume();
                    client.close();
                }
                else if(cmd.equalsIgnoreCase("reverse"))
                {
                    POSPClient client = new POSPClient();
                    client.connect("127.0.0.1", 3500);
                    client.consumeReverse();
                    client.close();
                }
                
                else if(cmd.equalsIgnoreCase("resetkey"))
                {
                    POSPClient client = new POSPClient();
                    client.connect("127.0.0.1", 3500);
                    client.resetKey();
                    client.close();
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
