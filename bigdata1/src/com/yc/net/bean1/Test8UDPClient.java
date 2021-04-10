package com.yc.net.bean1;

import javax.sql.DataSource;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * program:bigdata1
 * description:UDP客服端
 * author:lsj
 * create:2021-01-29 12:53
 */
public class Test8UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = null;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        ds = new DatagramSocket(5678);
        do{
            System.out.println("请输入你要发送的文字");
            String s = sc.nextLine();
            DatagramPacket dp = new DatagramPacket(s.getBytes(),
                    s.getBytes().length,
                    new InetSocketAddress("127.0.0.1",3333));
            ds.send(dp);
            if("bye".equalsIgnoreCase(s)){
                break;
            }
        }while(flag);
        System.out.println("客户端关闭");
        ds.close();
    }
}
