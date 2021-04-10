package com.yc.net.bean4;

import javax.management.monitor.Monitor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * program:bigdata1
 * description:ATM服务端
 * author:lsj
 * create:2021-03-08 22:30
 */
public class AtmServer {

    static  Boolean flag = true;

    public static void main(String[] args) throws Exception {
        Bank b = new Bank();
        ServerSocket ss = new ServerSocket(8887);
        System.out.println("银行服务器启动监听："+ss.getLocalPort()+"端口");
        Notify n = new Notify(){
            @Override
            public synchronized void notifyResult(boolean f){
                flag = f;
            }
        };
        Thread th = new Monitior(n,ss);
        th.start();

        while(flag){
            try {
                Socket s = ss.accept();
                System.out.println("有ATM客服端"+s.getRemoteSocketAddress()+"联接");
                Thread t = new Thread(new BankService(s,b));
                t.setDaemon(true);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("服务器正常关闭");
    }
}
interface Notify{
    public void notifyResult(boolean flag);
}
class Monitior extends Thread{
    private Notify n;
    private ServerSocket ss1;
    public Monitior(Notify n,ServerSocket ss1){
        this.ss1 = ss1;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9999);
            while(true){
                Socket s = ss.accept();
                System.out.println(s.getRemoteSocketAddress()+"联接上来");
                Scanner sc = new Scanner(s.getInputStream());
                String shift;
                if(sc.hasNext()){
                    shift = sc.nextLine();
                    if("STOP".equals(shift)){
                        if(n!=null){
                            n.notifyResult(false);
                        }
                        System.out.println("关闭银行服务器");
                        ss1.close();
                        break;
                    }else if("PAUSE".equals(shift)){
                        System.out.println("继续运行");
                    }else{
                        System.out.println("请重新输入");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
