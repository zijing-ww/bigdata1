package com.yc.net.bean4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * program:bigdata1
 * description:
 * author:lsj
 * create:2021-03-08 23:04
 */
public class ServerManager {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",9999);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println("STOP");
        pw.flush();
        s.close();
        System.out.println("客服端断开与服务器的联接");
    }
}
