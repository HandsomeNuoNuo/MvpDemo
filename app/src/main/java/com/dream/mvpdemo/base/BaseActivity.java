package com.dream.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.dream.mvpdemo.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;

/**BaseActivity
 * Created by Administrator on 2018/5/7.
 */

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView{

    protected P mPresenter;
    protected Context mContext;
    protected Toolbar toolbar;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mContext  =this;
        //初始化mPresenter
        initPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }

        //初始化
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 初始化mPresenter
     */
    protected abstract void initPresenter();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy()
    {
        if(mPresenter != null){
            mPresenter.disattachView(this);
        }
        super.onDestroy();
    }

    public void showSnackbar(String text)
    {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_SHORT).show();
    }
    public void showToast(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }
}
