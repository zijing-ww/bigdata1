package com.yc.threadpool;

import java.util.Vector;

/**
 * program:bigdata1
 * description:线程池管理类
 * author:lsj
 * create:2021-01-19 16:16
 */
public class ThreadPoolManageer {
    private Vector<SimpleThread> vector;
    private int maxThread;
    private static int coreCounts;

    static {
        coreCounts = Runtime.getRuntime().availableProcessors();
        System.out.println("系统核数："+coreCounts);
        System.out.println("线程池开始初始化");
    }

    public ThreadPoolManageer(){
        this(coreCounts);
    }

    public ThreadPoolManageer(int threadCount){
        vector = new Vector<SimpleThread>();
        for(int i=0;i<threadCount;i++){
            SimpleThread st = new SimpleThread();
            st.setName("线程"+(i+1));
            st.setDaemon(true);
            vector.add(st);
            st.start();
        }
    }

    public void process(TaskRunable task){
        if(task==null){
            return;
        }
        SimpleThread st = getFreeSimpleThread();
        st.setTask(task);
        st.setRunningFlag(true);
    }

    private SimpleThread getFreeSimpleThread() {
        int j=0;
        for(int i=0;i<vector.size();i++){
            SimpleThread stt = vector.get(i);
            j++;
            if(stt.isRunningFlag()==false){
                return stt;
            }
        }
        System.out.println("线程池没有空线程了，扩容"+coreCounts+"个新线程");
        for(int i=0;i<coreCounts;i++){
            SimpleThread st = new SimpleThread();
            st.setName("线程"+i+1);
            st.setDaemon(true);
            vector.add(st);
        }
        return vector.get(j);
    }
}
