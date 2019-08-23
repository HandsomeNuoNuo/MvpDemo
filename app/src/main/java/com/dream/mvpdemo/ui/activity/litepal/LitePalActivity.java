package com.dream.mvpdemo.ui.activity.litepal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.LitePalContract;
import com.dream.mvpdemo.model.bean.People;
import com.dream.mvpdemo.presenter.LitePalPresenter;

import java.util.List;

import butterknife.BindView;

public class LitePalActivity extends BaseActivity<LitePalPresenter> implements LitePalContract.View
{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PeopleAdapter mAdapter;

    @Override
    protected void initPresenter()
    {
        mPresenter = new LitePalPresenter();
    }

    @Override
    protected void initView()
    {
        toolbar.setTitle("LitePal");
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PeopleAdapter();
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        // recyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(recyclerView);
        mPresenter.getAllPeople();
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_litepal;
    }

    @Override
    public void saveOK(People people)
    {
        //Toast.makeText(mContext,"保存成功",Toast.LENGTH_SHORT).show();
        mAdapter.addOneData(people);
        showSnackbar("保存成功");
    }

    @Override
    public void saveFail()
    {
        Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setView(List<People> list)
    {
        mAdapter.setData(list);
    }

    public void save(View v)
    {
        mPresenter.savePeople("小明", 16, "男");
    }
}
