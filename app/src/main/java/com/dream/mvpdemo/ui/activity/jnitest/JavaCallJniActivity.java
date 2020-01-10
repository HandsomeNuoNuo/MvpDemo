package com.dream.mvpdemo.ui.activity.jnitest;

import android.util.Log;
import android.widget.TextView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.protocol.JniProtocol;

import javax.security.auth.login.LoginException;

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

        int []a = {1,2,3,4,5,6,7,8,9,10};
        int []b = JniProtocol.getInstance().testArray(a,20);

        for (int j = 0;j < b.length;j++){
            Log.e("test","b[] = " + b[j]);
        }
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
