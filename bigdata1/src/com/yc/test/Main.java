package com.yc.test;

public class Main {

    public static void main(String[] args) {
	    //1.单线程
        System.out.println("hello world");
        a();
        System.out.println("a");
        b();
        System.out.println("b");
    }

    private static void b() {
        System.out.println("aa");
    }

    private static void a() {
        System.out.println("a");
    }
}
