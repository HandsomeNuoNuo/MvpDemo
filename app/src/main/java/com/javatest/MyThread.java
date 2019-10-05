package com.javatest;

import java.util.List;

/**********************************
 * @Name: MyThread
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/26 14:10
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class MyThread extends Thread{
    private List<Integer> list;

    public MyThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            if(list != null &&list.size() > 0){
                System.out.println("x = " + list.get(0));
                list.remove(0);
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

