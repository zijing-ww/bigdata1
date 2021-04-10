package com.yc.test;

import java.util.Date;

/**
 * program:bigdata1
 * description:线程的分类
 * author:lsj
 * create:2021-01-15 15:33
 */
public class Test6_threadTypes {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("名字"+t.getName());
        System.out.println("编号"+t.getId());
        System.out.println("优先级"+t.getPriority());
        System.out.println("线程组"+t.getThreadGroup());

        ThreadGroup tg = new ThreadGroup("线程组");
        tg.setMaxPriority(10);

        Thread t2 = new Thread(tg,new Clock());
        t2.setDaemon(true);
        t2.setName("新线程");
        t2.setPriority(10);
        System.out.println(t2.getPriority());
        t2.start();
    }
}

class Clock implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName()+":"+new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
