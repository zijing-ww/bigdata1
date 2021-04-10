package com.yc.net.bean4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * program:bigdata1
 * description:
 * author:lsj
 * create:2021-03-08 22:53
 */
public class BankService implements Runnable{

    private Socket s;
    private Bank b;
    private Scanner reader;
    private PrintWriter pw;
    private boolean flag;

    public BankService(Socket s,Bank b){
        try {
            this.s = s;
            this.b = b;
            System.out.println("ATM客户端"+s.getInetAddress()+"联接了服务器");
            reader = new Scanner(s.getInputStream());
            pw = new PrintWriter(s.getOutputStream());
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(flag){
            if(!reader.hasNext()){
                System.out.println("ATM客户端"+s.getRemoteSocketAddress()+"掉线了");
                break;
            }
            String command = reader.next();
            if(command.equals("QUIT")){
                System.out.println("ATM客户端"+s.getRemoteSocketAddress()+"掉线了");
                break;
            }
            int accountId = reader.nextInt();
            try {
                BankAccount ba = null;
                if(command.equals("DEPOSIT")){
                    double amount = reader.nextDouble();
                    ba = b.deposite(accountId,amount);
                }else if(command.equals("WITHDRAW")){
                    double amount = reader.nextDouble();
                    ba = b.withdraw(accountId,amount);
                }else if(!command.equals("BALANCE")){
                    pw.println("错误的命令");
                    pw.flush();
                    return;
                }
                ba = b.search(accountId);
                pw.println(ba.getId()+" "+ba.getBalance());
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
                pw.flush();
            }
        }
    }
}
