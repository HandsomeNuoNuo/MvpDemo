package com.dream.mvpdemo.ui.activity.sdktest;

import android.widget.TextView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.hf.mylibrary.SDKTest;

import butterknife.BindView;

/******************************************************************
 * @文件名称 : SDKTestActivity
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/30 11:58
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/30
 ******************************************************************/
public class SDKTestActivity extends BaseActivity
{
    private final String TAG = "SDKTestActivity";

    @BindView(R.id.tv_value)
    TextView tvValue;


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
        SDKTest.init(this);

        tvValue.setText(SDKTest.readMetaDataFromApplication());
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_sdktest;
    }

}
