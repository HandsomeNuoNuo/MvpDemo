package com.dream.mvpdemo.ui.activity.ecg;

import android.util.Log;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;

/******************************************************************
 * @文件名称 : ECGActivity
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/29 9:09
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/29
 ******************************************************************/
public class ECGActivity extends BaseActivity
{
    private final String TAG = "ECGActivity";

    @BindView(R.id.ecgView)
    ECGView ecgView;

    private volatile boolean run = true;

    /**
     * 初始化mPresenter
     */
    @Override
    protected void initPresenter()
    {

    }

    /**
     * 初始化
     */
    @Override
    protected void initView()
    {
        toolbar.setTitle("ECG");
        Log.i("test", "toolbar.getHeight() = " + toolbar.getHeight());
        new MyTread().start();
    }

    @Override
    protected void onDestroy()
    {
        run = false;
        super.onDestroy();
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_ecg;
    }

    class MyTread extends Thread
    {
        int i = 0;

        public MyTread()
        {
        }

        @Override
        public void run()
        {
            while (run)
            {
                i = new Random().nextInt(200);
                ecgView.pushData(i);
                try
                {
                    sleep(60);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
