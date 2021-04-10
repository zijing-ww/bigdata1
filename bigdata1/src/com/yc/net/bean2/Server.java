package com.yc.net.bean2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * program:bigdata1
 * description:服务端
 * author:lsj
 * create:2021-01-30 14:50
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8887);
        System.out.println("服务器启动了"+ss.getLocalPort()+"端口");
        while(true){
            Socket s = ss.accept();
            Thread t = new Thread(new TalkTask(s));
            t.start();
        }
    }
}
class TalkTask implements Runnable{
    private Socket s;
    private Scanner sc = new Scanner(System.in);

    public TalkTask(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        try(Socket s = this.s;
            BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());
        ){
            System.out.println("一个新的客户端"+s.getRemoteSocketAddress()+"连接成功");
            String line = null;
            do{
                line = is.readLine();
                System.out.println("客户端说"+line);
                if("bye".equalsIgnoreCase(line)){
                    System.out.println("客户端主动断线");
                    break;
                }
                System.out.println("输入你想对客户端说的话");
                String response = sc.nextLine();
                pw.println(response);
                pw.flush();
                if("bye".equalsIgnoreCase(response)){
                    System.out.println("服务器主动断开与客户端的连接");
                    break;
                }
            }while(true);
            System.out.println("服务器"+s.getLocalSocketAddress()+"断开与客户端"+s.getRemoteSocketAddress()+"连接");
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("客户端"+this.s.getRemoteSocketAddress()+"掉线");
        }
    }
}
