package com.yc.net.bean1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * program:bigdata1
 * description:服务端
 * author:lsj
 * create:2021-01-27 14:13
 */
public class Test5Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8887);
        System.out.println("服务器"+server.getLocalSocketAddress()+"启动了，监听"+server.getLocalPort());
        boolean flag = true;
        while(flag){
            System.out.println("服务器"+server.getLocalSocketAddress()+"在等待客服端的连接");
            Socket client = server.accept();
            System.out.println("一个新的客户端"+client.getRemoteSocketAddress()+"连接成功");
            OutputStream os = client.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            InputStream is = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            do{
                line = br.readLine();
                System.out.println("客户端说"+line);
                if("bye".equalsIgnoreCase(line)){
                    System.out.println("客户端主动断线");
                    break;
                }
                System.out.println("请输入你想回应客户端得话");
                String response = brin.readLine();
                pw.println(response);
                pw.flush();
                if("bye".equalsIgnoreCase(line)){
                    System.out.println("服务端主动断开与客户端的连接");
                    break;
                }
            }while (true);
            System.out.println("服务器"+client.getLocalSocketAddress()+"断开与客户端"+client.getRemoteSocketAddress());
            pw.flush();
            pw.close();
            os.flush();
            os.close();
            brin.close();
            br.close();
            is.close();
            client.close();
        }
        System.out.println("服务器关闭");
        server.close();
    }
}
