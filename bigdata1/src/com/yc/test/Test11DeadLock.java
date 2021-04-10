package com.yc.test;

/**
 * program:bigdata1
 * description:死锁案例
 * author:lsj
 * create:2021-01-15 20:21
 */
public class Test11DeadLock {
    /** A锁 */
    private static String A = "A";
    /** B锁 */
    private static String B = "B";
    public static void main(String[] args) {
        new Test11DeadLock().deadLock();
    }
    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
