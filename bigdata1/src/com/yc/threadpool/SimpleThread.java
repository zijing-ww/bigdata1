package com.yc.threadpool;

/**
 * program:bigdata1
 * description:线程类
 * author:lsj
 * create:2021-01-19 16:09
 */
public class SimpleThread extends Thread{
    private TaskRunable task;
    private boolean runningFlag;

    public SimpleThread(){
        this.runningFlag = false;
        System.out.println("线程"+this.getName()+"实例完成，进入创建态");
    }

    public boolean isRunningFlag(){
        return runningFlag;
    }

    public synchronized void setRunningFlag(boolean flag){
        this.runningFlag = flag;
        if(this.runningFlag){
            System.out.println(this.getName()+"激活了");
            this.notify();
        }
    }

    public void setTask(TaskRunable task){
        this.task = task;
    }

    @Override
    public void run() {
        while(true){
            if(runningFlag==false){
                try {
                    synchronized (this){
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                if(this.task!=null){
                    this.task.doTask();
                    setRunningFlag(false);
                    System.out.println(Thread.currentThread().getName()+"运行完了");
                }
            }
        }
    }
}
