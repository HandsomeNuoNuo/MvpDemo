package com.dream.mvpdemo.manager;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/******************************************************************
 * @文件名称 : ThreadPoolManager
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/26 10:46
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/26
 ******************************************************************/
public class ThreadPoolManager
{
    private final String TAG = "ThreadPoolManager";

    private ExecutorService mThreadPool;

    private static ThreadPoolManager instance;

    private ThreadPoolManager(){
        int coreNum = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(coreNum);
    }

    public static ThreadPoolManager getInstance()
    {
        if (instance == null) instance = new ThreadPoolManager();
        return instance;
    }

    public void submit(Runnable r){
        mThreadPool.execute(r);
    }


    //通过阻塞来停止死循环
    public void release(){
        Log.i(TAG,"mThreadPool.release");
        if(mThreadPool==null)return;
        mThreadPool.shutdown();
        try {
            //等待线程池中线程执行完毕，最长等待时间5分钟
            mThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            mThreadPool.shutdownNow();
        } catch (InterruptedException e) {
            Log.e(TAG,e.getMessage());
        }
    }

}
