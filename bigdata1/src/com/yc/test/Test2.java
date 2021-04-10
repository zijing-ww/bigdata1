package com.yc.test;

import java.util.Date;

public class Test2 {
    public static void main(String[] args) {
        //并行
        Thread t1 = new Thread(new ShowTime());
        t1.start();
        Thread t2 = new Thread(new Download());
        t2.start();
    }
}

class ShowTime implements Runnable{

    @Override
    public void run() {
        //操作
        while(true){
            System.out.println(new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//下载是一个耗时的操作
class Download implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100000;i++){
            if(i%1000==0){
                System.out.println("下载数据："+i);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
