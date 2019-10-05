package com.dream.mvpdemo.ui.activity.viewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**********************************
 * @Name: ViewTestActivity
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/30 10:50
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class ViewTestActivity extends BaseActivity {
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_rm)
    Button btnRm;
    @BindView(R.id.tv_green)
    TextView tvGreen;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewtest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_add, R.id.btn_rm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                tvGreen.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_rm:
                tvGreen.setVisibility(View.GONE);
                break;
        }
    }
}
