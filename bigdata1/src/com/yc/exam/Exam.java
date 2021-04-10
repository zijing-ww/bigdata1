package com.yc.exam;

import java.util.Random;

/**
 * program:bigdata1
 * description:模拟考试倒计时效果
 * author:lsj
 * create:2021-01-20 19:57
 */
public class Exam {
    public static void main(String[] args) {
        //监听器
        NotifyListener nl = new MyNotify();
        //计时任务
        Thread t = new CountTime(nl,10);  
        t.start();

        Student s = new Student(nl);
        s.setName("张三");
        Student s2 = new Student(nl);
        s2.setName("李四");

        s.start();
        s2.start();
    }
}
class Student extends Thread{
    private NotifyListener listener;

    public Student(NotifyListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("学生"+this.getName()+"正在做"+(i+1)+"题");
            try {
                Thread.sleep(new Random().nextInt(4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.listener!=null&&this.listener instanceof MyNotify){
                if(((MyNotify)listener).isFinish()){
                    break;
                }
            }
        }
    }
}
interface NotifyListener{
    public void notifyfinish(boolean isFinish);
}
class MyNotify implements NotifyListener{
    private boolean isFinish;
    @Override
    public void notifyfinish(boolean isFinish) {
        this.isFinish = isFinish;
    }
    public boolean isFinish(){
        return isFinish;
    }
}
class CountTime extends Thread{
    /**
     * 监听器的对象
     */
    private NotifyListener notifyListener;
    /**
     * 总时间
     */
    private int elipsTime;
    /**
     * 已用的时间
     */
    private int count;
    /**
     * 标识当前计时任务的状态
     */
    private boolean flag = true;

    public CountTime(NotifyListener notifyListener,int elipsTime){
        this.notifyListener = notifyListener;
        this.elipsTime = elipsTime;
    }

    @Override
    public void run() {
        System.out.println("计时开始");
        while (flag){
            System.out.println("距离考试结束还有："+(elipsTime-count)+"秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            if(count==elipsTime){
                flag = false;
                System.out.println("考试时间到，强制交卷");
                if(this.notifyListener!=null){
                    this.notifyListener.notifyfinish(true);
                }
            }
        }
    }
}