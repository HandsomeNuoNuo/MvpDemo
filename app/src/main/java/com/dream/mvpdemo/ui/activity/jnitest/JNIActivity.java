package com.dream.mvpdemo.ui.activity.jnitest;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class JNIActivity extends BaseActivity
{
    @BindView(R.id.btn_jniCPP)
    Button btn_jniCPP;

    @BindView(R.id.btn_jniJava)
    Button btn_jniJava;

    @BindView(R.id.btn_ecpp)
    Button btnEcpp;

    @BindView(R.id.btn_ejava)
    Button btnEjava;

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
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_jni;
    }

    @OnClick({R.id.btn_jniCPP , R.id.btn_jniJava, R.id.btn_ecpp, R.id.btn_ejava})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_jniCPP:startActivity(new Intent(mContext, JavaCallJniActivity.class));
                break;
            case R.id.btn_jniJava:startActivity(new Intent(mContext, JNICallJavaActivity.class));
                break;
            case R.id.btn_ecpp:
                break;
            case R.id.btn_ejava:
                break;
        }
    }
}
