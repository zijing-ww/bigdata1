package com.yc.test;

/**
 * program:bigdata1
 * description:获取当前系统有多少个内核
 * author:lsj
 * create:2021-01-20 20:23
 */
public class Test13 {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
    }
}
