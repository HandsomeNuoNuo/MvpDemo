package com.dream.mvpdemo.ui.activity.litepal;

import com.dream.mvpdemo.base.IBaseModel;
import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;
import com.dream.mvpdemo.model.bean.People;

import java.util.List;

/**
 * LitePalContract
 * Created by Administrator on 2018/5/7.
 */

public interface LitePalContract
{
    interface LitePalModel extends IBaseModel
    {
        People savePeople(String name,int age,String sex);
        List<People> getAllPeople();
    }


    interface View extends IBaseView
    {
        void saveOK(People people);
        void saveFail();
        void setView(List<People> list);
    }

    interface Presenter extends IBasePresenter<View>
    {
        void savePeople(String name,int age,String sex);
        void getAllPeople();
    }
}
