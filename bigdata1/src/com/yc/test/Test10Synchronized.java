package com.yc.test;

/**
 * program:bigdata1
 * description:写一个程序模拟火车站卖票的场景
 * author:lsj
 * create:2021-01-15 20:09
 */
public class Test10Synchronized {
    public static void main(String[] args) {
        TicketCounterTask tct = new TicketCounterTask(100);

        Thread t1 = new Thread(tct);
        t1.setName("窗口一");
        t1.start();
        Thread t2 = new Thread(tct);
        t2.setName("窗口二");
        t2.start();
        Thread t3 = new Thread(tct);
        t3.setName("窗口三");
        t3.start();
    }
}
class TicketCounterTask implements Runnable{
    private int total;
    Object o = new Object();
    public TicketCounterTask(int total){
        this.total = total;
    }
    @Override
    public void run() {
        while (true){
            synchronized (this){
                if(total<=0){
                    return;
                }
                System.out.println("线程"+Thread.currentThread().getName()+"票号"+total);
                total--;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private synchronized boolean doThing(){
        if(total<=0){
            return true;
        }
        System.out.println("线程"+Thread.currentThread().getName()+"票号"+total);
        total--;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}