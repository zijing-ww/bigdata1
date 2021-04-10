package com.yc.test;

/**
 * program:bigdata1
 * description:join的测试
 * author:lsj
 * create:2021-01-15 16:26
 */
public class Test7 {
    public static void main(String[] args) {
        Thread t = new Thread(new Task7());
        t.setName("新线程");
        t.setPriority(10);
        t.start();

//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread.currentThread().setPriority(10);
        Thread.yield();
        for(int i=0;i<999;i++){
            System.out.println("线程"+Thread.currentThread().getName()+"输出"+i);
        }
    }
}

class Task7 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<999;i++){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("线程"+Thread.currentThread()+"输出"+i);
        }
    }
}