package com.dream.mvpdemo.ui.activity.jnitest;

import android.widget.TextView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.protocol.JniProtocol;

import butterknife.BindView;

public class JavaCallJniActivity extends BaseActivity
{

    @BindView(R.id.textview)
    TextView textview;

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
        toolbar.setTitle("JNI");

        textview.setText(JniProtocol.getInstance().stringFromJNI());
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_jnidemo;
    }
}
