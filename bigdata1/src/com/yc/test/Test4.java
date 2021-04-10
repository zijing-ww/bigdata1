package com.yc.test;

/**
 * program:bigdata1
 * description:Priority
 * author:lsj
 * create:2021-01-13 22:07
 */
public class Test4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ShowNumber());
        t1.setName("线程一");
        t1.setPriority(Thread.MIN_PRIORITY);

        Thread t2 = new Thread(new ShowNumber());
        t2.setName("线程二");
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}

class ShowNumber implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<9999999;i++){
            if(i%10000==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}