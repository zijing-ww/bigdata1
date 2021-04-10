package com.yc.net.bean1;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * program:bigdata1
 * description:服务端
 * author:lsj
 * create:2021-01-23 17:38
 */
public class Test3Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务器"+server.getLocalSocketAddress()+"启动了，监听"+server.getLocalPort());
        while(true){
            System.out.println("服务器"+server.getLocalSocketAddress()+"在等待客服端的连接");
            Socket client = server.accept();
            System.out.println("一个新的客户端"+client.getRemoteSocketAddress()+"连接成功");
            OutputStream os = client.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("hello"+client.getInetAddress()+":"+client.getPort());
            dos.flush();
            os.flush();
            os.close();
            dos.close();
            client.close();
        }
    }
}
