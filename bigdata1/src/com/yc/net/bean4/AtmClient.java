package com.yc.net.bean4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * program:bigdata1
 * description:ATM机的存储，转账的客户端
 * author:lsj
 * create:2021-03-08 22:21
 */
public class AtmClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8887);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner sc = new Scanner(s.getInputStream());
        boolean flag = true;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("============");
            System.out.println("1.存");
            System.out.println("2.取");
            System.out.println("3.查询");
            System.out.println("4.退出");
            System.out.println("============");
            String command = input.nextLine();
            String line;
            if ("1".equals(command)) {
                pw.println("DEPOSIT 1 100");
                pw.flush();
                line = sc.nextLine();
                System.out.println("服务器的响应为：" + line);
            } else if ("2".equals(command)) {
                pw.println("WITHDRAW 1 10");
                pw.flush();
                line = sc.nextLine();
                System.out.println("服务器的响应为：" + line);
            } else if ("3".equals(command)) {
                pw.println("BALANCE 1");
                pw.flush();
                line = sc.nextLine();
                System.out.println("服务器的响应为：" + line);
            }else{
                pw.println("QUIT");
                pw.flush();
                flag = false;
            }
        } while (flag);
        s.close();
        System.out.println("客户端断开与服务器的联接");
    }
}
