package com.yc.net.bean1;

import java.io.*;
import java.net.Socket;

/**
 * program:bigdata1
 * description:客服端
 * author:lsj
 * create:2021-01-27 15:34
 */
public class Test5Client {
    static int port = 8887;
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localHost",port);
        System.out.println(client.getLocalSocketAddress()+"连上了服务器"+client.getRemoteSocketAddress());
        OutputStream os = client.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        InputStream is = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        do{
            System.out.println("输入你想对服务器说的话");
            line = brin.readLine();
            pw.println(line);
            pw.flush();
            System.out.println("客服端发送成功"+line);
            if("bye".equalsIgnoreCase(line)){
                System.out.println("客户端主动断开与服务器的连接");
                break;
            }
            String serverwords = br.readLine();
            System.out.println("服务器说"+serverwords);
            if("bye".equalsIgnoreCase(serverwords)){
                System.out.println("服务端要求断开与客户端的连接");
                break;
            }
        }while (true);
        System.out.println("客户端断开连接");
        pw.flush();
        pw.close();
        os.flush();
        os.close();
        brin.close();
        br.close();
        is.close();
        client.close();
    }
}
