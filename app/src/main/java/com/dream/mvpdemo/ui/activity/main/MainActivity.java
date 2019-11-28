package com.dream.mvpdemo.ui.activity.main;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.model.bean.House;
import com.dream.mvpdemo.ui.activity.ble.scan.BleListActivity;
import com.dream.mvpdemo.ui.activity.jnitest.JNIActivity;
import com.dream.mvpdemo.ui.activity.litepal.LitePalActivity;
import com.dream.mvpdemo.ui.activity.sdktest.SDKTestActivity;
import com.dream.mvpdemo.ui.activity.ecg.ECGActivity;
import com.dream.mvpdemo.ui.activity.share.ShareActivity;
import com.dream.mvpdemo.ui.activity.viewtest.ViewTestActivity;
import com.kotlin.KotlinActivity;

import org.litepal.crud.SaveHandler;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/******************************************************************
 * @文件名称 : MainActivity
 * @文件作者 : 黄峰
 * @创建时间 : 2019/7/19 10:36
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/7/19
 ******************************************************************/
@RuntimePermissions
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
        builder.setDoor("我是一扇门")
                .addWindow("我是第一扇窗")
                .addWindow("我是第二扇窗")
                .setStep("我是一层楼梯")
                .setPrice(20000);
        House house = builder.build();

        Log.i("test", "房子的构造：" + house.door + "  " + house.window + "   " + house.step);
        Log.i("test", "售价：" + house.price);
        MainActivityPermissionsDispatcher.getPremissionWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void getPremission()
    {

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


    @OnClick({R.id.btn_litepal, R.id.btn_kotlin, R.id.btn_jni, R.id.btn_ecg, R.id.btn_sdk, R.id.btn_per,R.id.btn_ble,R.id.btn_viewtest,R.id.btn_share})
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
            case R.id.btn_ecg:
                startActivity(new Intent(mContext, ECGActivity.class));
                break;
            case R.id.btn_sdk:
                startActivity(new Intent(mContext, SDKTestActivity.class));
                break;
            case R.id.btn_per:
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:10086");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.btn_ble:
                startActivity(new Intent(mContext, BleListActivity.class));
                break;
            case R.id.btn_viewtest:
                startActivity(new Intent(mContext, ViewTestActivity.class));
                break;
            case R.id.btn_share:
                startActivity(new Intent(mContext, ShareActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
