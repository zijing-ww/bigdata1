package com.yc.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * program:bigdata1
 * description:测试线程池类
 * author:lsj
 * create:2021-01-19 16:26
 */
public class Test2ThreadPool {
    public static void main(String[] args) {
        ThreadPoolManageer tpm = new ThreadPoolManageer();
        InputStream iis = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(iis));
        String s = null;
        while (true){
            try {
                if ((s=br.readLine())==null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            tpm.process(new MyTask(s));
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyTask implements TaskRunable{
    String content;
    public MyTask(String content){
        this.content = content;
    }
    @Override
    public void doTask() {
        System.out.println(Thread.currentThread().getName()+"正在运行"+this.content);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
