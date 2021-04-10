package com.yc.net.bean1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * program:bigdata1
 * description:客户端
 * author:lsj
 * create:2021-01-23 17:33
 */
public class Test2Client {
    public static int port = 9999;

    public static void main(String[] args) throws IOException {
        Socket client = null;
        OutputStream os = null;
        DataOutputStream dos = null;
        try{
            client = new Socket("localHost",port);
            System.out.println(client.getLocalSocketAddress()+"连上了服务器"+client.getRemoteSocketAddress());
            os = client.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeUTF("hello server abc");
            dos.flush();
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
