package com.dream.mvpdemo.ui.activity.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.model.bean.House;
import com.dream.mvpdemo.ui.activity.jnitest.JNIActivity;
import com.dream.mvpdemo.ui.activity.litepal.LitePalActivity;
import com.kotlin.KotlinActivity;

import butterknife.BindView;
import butterknife.OnClick;

/******************************************************************
 * @文件名称 : MainActivity
 * @文件作者 : 黄峰
 * @创建时间 : 2019/7/19 10:36
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/7/19
 ******************************************************************/
public class MainActivity extends BaseActivity
{
    @BindView(R.id.btn_litepal)
    Button btnLitepal;

    @BindView(R.id.btn_kotlin)
    Button btnKotlin;

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
        actionBar.setDisplayHomeAsUpEnabled(false);

        House.Builder builder = new House.Builder();
        builder.setDoor("我是一扇门").setWindow("我是一扇窗").setStep("我是一层楼梯").setPrice(20000);
        House house = builder.build();

        Log.i("test","房子的构造：" + house.door+"  "+house.window + "   "+ house.step);
        Log.i("test","售价：" +house.price);
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_main;
    }


    @OnClick({R.id.btn_litepal, R.id.btn_kotlin , R.id.btn_jni})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_litepal:
                startActivity(new Intent(mContext, LitePalActivity.class));
                break;
            case R.id.btn_kotlin:
                startActivity(new Intent(mContext, KotlinActivity.class));
                break;
            case R.id.btn_jni:
                startActivity(new Intent(mContext, JNIActivity.class));
                break;
        }
    }
}
