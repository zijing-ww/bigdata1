package com.yc.test;

/**
 * program:bigdata1
 * description:Yeild
 * author:lsj
 * create:2021-01-13 22:16
 */
public class Test {
    public static void main(String[] args) {
        YeildTask y1 = new YeildTask();
        YeildTask y2 = new YeildTask();

        Thread t1 = new Thread(y1,"员工");
        Thread t2 = new Thread(y2,"领导");

        System.out.println(t1.getPriority()+"\t"+t2.getPriority());

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}

class YeildTask implements Runnable{
    @Override
    public void run() {
        if("员工".equalsIgnoreCase(Thread.currentThread().getName())){
            Thread.yield();
        }
        for(int i=0;i<9999999;i++){
            if(i%10000==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}