package com.yc.test;

import java.util.Date;

/**
 * program:bigdata1
 * description:测试3
 * author:lsj
 * create:2021-01-13 21:59
 */
public class Test3 {
    public static void main(String[] args) {
        ShowTime2Thread stt2 = new ShowTime2Thread();
        stt2.start();
    }
}

class ShowTime2Thread extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println(new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}