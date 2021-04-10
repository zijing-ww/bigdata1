package com.yc.net.bean1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * program:bigdata1
 * description:客户端
 * author:lsj
 * create:2021-01-23 18:07
 */
public class Test4Client {
    public static int port = 9999;
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localHost",port);
        System.out.println(client.getLocalSocketAddress()+"连上了服务器"+client.getRemoteSocketAddress());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String str = dis.readUTF();
        System.out.println(str);
        dos.writeUTF("你也hello");
        dos.flush();
        dos.close();
        dis.close();
        client.close();
    }
}
