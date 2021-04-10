package com.yc.test;

/**
 * program:bigdata1
 * description:whyjoin及多线程与单线程效率比较
 * author:lsj
 * create:2021-01-15 19:55
 */
public class Test8Whyjoin {
    /** 执行次数 */
    private static final long count = 10000L;
    public static void main(String[] args) throws InterruptedException {
        //并发计算
        concurrency();
        //单线程计算
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {    //执行累加操作
                    a += 5;
                }
                System.out.println(a);
            }
        });
        thread.start();  //启动新线程  ,注意此时 新线程与下面的代码 b--  是同时运行的
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        //为什么这里要用   join()呢? 请看解释
        thread.join();
        long time = System.currentTimeMillis() - start;
        //并行性能
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        //执行累加操作
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        //串行性能
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
