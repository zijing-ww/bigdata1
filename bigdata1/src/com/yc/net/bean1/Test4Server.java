package com.yc.net.bean1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * program:bigdata1
 * description:服务端
 * author:lsj
 * create:2021-01-23 18:03
 */
public class Test4Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务器"+server.getLocalSocketAddress()+"启动了，监听"+server.getLocalPort());
        while(true){
            System.out.println("服务器"+server.getLocalSocketAddress()+"在等待客服端的连接");
            Socket client = server.accept();
            System.out.println("一个新的客户端"+client.getRemoteSocketAddress()+"连接成功");
            OutputStream os = client.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = client.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            dos.writeUTF("hello"+client.getInetAddress()+":"+client.getPort());
            String msg = dis.readUTF();
            System.out.println(msg);
            dis.close();
            is.close();
            dos.flush();
            os.flush();
            os.close();
            dos.close();
            client.close();
        }
    }
}
