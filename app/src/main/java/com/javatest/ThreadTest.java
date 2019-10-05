package com.javatest;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**********************************
 * @Name: ThreadTest
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/26 14:03
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class ThreadTest {

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        MyThread myThread = new MyThread(list);
        new Thread(() -> {
            while (true){
                list.add(new Random().nextInt(100));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

//        Handler handler = new Handler();
//        Runnable runnable = () -> {
//            list.add(new Random().nextInt(100));
//            handler.postDelayed(runnable,1000);
//        };
//        handler.postDelayed(runnable,100);
        myThread.start();
    }

}
