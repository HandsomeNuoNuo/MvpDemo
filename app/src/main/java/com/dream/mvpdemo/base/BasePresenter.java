package com.dream.mvpdemo.base;

/**BasePresenter
 * Created by Administrator on 2018/5/7.
 */

public abstract class BasePresenter<V extends IBaseView ,M extends IBaseModel> implements IBasePresenter<V> {

    protected M model;
    protected V mView;

     public BasePresenter(){
         init();
         initModel();
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void disattachView(V view)
    {
        mView = null;
    }
}
