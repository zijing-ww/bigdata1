package com.yc.net.bean3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * program:bigdata1
 * description:客户端
 * author:lsj
 * create:2021-01-30 14:23
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("客户端启动");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try(Socket s = new Socket(InetAddress.getLocalHost(),8887);
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            InputStream iss = s.getInputStream();
            BufferedReader bris = new BufferedReader(new InputStreamReader(iss));
        ){
            System.out.println("客服端连接"+s.getRemoteSocketAddress()+"成功");
            boolean flag = true;
            do{
                System.out.println("请输入你想对服务器说的话");
                String line = br.readLine();
                pw.println(line);
                pw.flush();
                System.out.println("客户端发送成功");
                if("bye".equalsIgnoreCase(line)){
                    System.out.println("客户端主动断开与客服端的连接");
                    break;
                }
                String serverwords = bris.readLine();
                System.out.println("服务器说"+serverwords);
                if("bye".equalsIgnoreCase(serverwords)){
                    System.out.println("服务器要求断开与客户端的连接");
                    break;
                }
            }while(flag);
            pw.flush();
            System.out.println("客户端断开连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
