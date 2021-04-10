package com.yc.net.bean1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * program:bigdata1
 * description:服务端
 * author:lsj
 * create:2021-01-23 16:58
 */
public class Test2Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务器"+server.getLocalSocketAddress()+"启动了，监听"+server.getLocalPort());
        while(true){
            System.out.println("服务器"+server.getLocalSocketAddress()+"在等待客服端的连接");
            Socket s = server.accept();
            System.out.println("一个新的客户端"+s.getRemoteSocketAddress()+"连接成功");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String response = dis.readUTF();
            System.out.println(response);
            dis.close();
            s.close();
            System.out.println("客户端"+s.getRemoteSocketAddress()+"连接完毕，掉线");
        }
    }
}
