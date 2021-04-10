package com.yc.test;

import java.util.Random;

/**
 * program:bigdata1
 * description:生产者和消费者
 * author:lsj
 * create:2021-01-15 20:47
 */
public class Test12ProducerAndConsumer {
    public static void main(String[] args) {
        AppleBox box = new AppleBox();

        ProducerTask pt = new ProducerTask(box);
        ConsumerTask ct = new ConsumerTask(box);

        Thread t1 = new Thread(pt);
        t1.setName("生产者");
        t1.start();

        Thread t2 = new Thread(ct);
        t2.setName("消费者");
        t2.start();
    }
}
class ProducerTask implements Runnable{
    AppleBox appleBox;
    public ProducerTask(AppleBox appleBox){
        this.appleBox = appleBox;
    }
    @Override
    public void run() {
        Random r = new Random();
        for(int i=0;i<6;i++){
            Apple a = new Apple(i);
            appleBox.deposite(a);
            try {
                Thread.sleep(r.nextInt(3)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ConsumerTask implements Runnable{
    AppleBox appleBox;
    public ConsumerTask(AppleBox appleBox){
        this.appleBox = appleBox;
    }
    @Override
    public void run() {
        Random r = new Random();
        for(int i=0;i<6;i++){
            appleBox.withDraw();
            try {
                Thread.sleep(r.nextInt(3)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class AppleBox{
    Apple [] apples = new Apple[5];
    int index = 0;
    public synchronized void deposite(Apple apple){
        while (index>=apples.length){
            try {
                this.wait();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        apples[index] = apple;
        index++;
        System.out.println(Thread.currentThread().getName()+"生产了苹果"+apple);
    }
    public synchronized void withDraw(){
        while (index==0){
            try {
                this.wait();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        Apple a = apples[index-1];
        index--;
        System.out.println(Thread.currentThread().getName()+"消费了苹果"+a);
    }
}
class Apple{
    int id;
    Apple(int id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Apple" + id;
    }
}