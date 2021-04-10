package com.yc.net.bean1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * program:bigdata1
 * description:UDP服务端
 * author:lsj
 * create:2021-01-29 12:50
 */
public class Test9UDPServer {
    public static void main(String[] args) throws Exception {
        byte [] buf = new byte[2];
        DatagramPacket dp = new DatagramPacket(buf,buf.length);
        DatagramSocket ds = new DatagramSocket(3333);
        boolean flag = true;
        while(flag){
            ds.receive(dp);
            System.out.println(dp.getLength());
            System.out.println(new String(buf,0,dp.getLength()));
        }
    }
}
