package com.dream.mvpdemo.ui.activity.litepal;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.mvpdemo.R;
import com.dream.mvpdemo.model.bean.People;

import java.util.List;

/******************************************************************
 * @文件名称 : PeopleAdapter
 * @文件作者 : 黄峰
 * @创建时间 : 2019/7/23 9:50
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/7/23
 ******************************************************************/
public class PeopleAdapter extends BaseQuickAdapter<People, BaseViewHolder>
{
    private final String TAG = "PeopleAdapter";


    public PeopleAdapter()
    {
        super(R.layout.list_item_people);
    }

    public void setData(List<People> list){
        this.setNewData(list);

    }

    public void addOneData(People people){
        this.addData(people);

    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, People item)
    {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_sex, item.getSex())
                .setText(R.id.tv_age, item.getAge() + "");

    }
}
