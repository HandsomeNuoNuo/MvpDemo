package com.dream.mvpdemo.ui.activity.litepal;

import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;
import com.dream.mvpdemo.model.bean.People;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * LitePalPresenter
 * Created by Administrator on 2018/5/7.
 */

public class LitePalPresenter extends BasePresenter<LitePalContract.View, LitePalContract.LitePalModel> implements LitePalContract.Presenter
{
    private CompositeDisposable compositeDisposable;

    @Override
    public void init()
    {
        Log.i("test", "init");
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void initModel()
    {
        model = new LitePalModel();
    }

    @Override
    public void savePeople(String name, int age, String sex)
    {
        People people = model.savePeople(name, age, sex);
        if (people != null)
        {
            mView.saveOK(people);
        } else
        {
            mView.saveFail();
        }
    }

    @Override
    public void getAllPeople()
    {

//        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<People>>()
//        {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<List<People>> emitter) throws Exception
//            {
//                List<People> list = model.getAllPeople();
//                if (list == null || list.size() == 0)
//                {
//                    emitter.onError(new Exception("没有数据"));
//                } else
//                {
//                    emitter.onNext(model.getAllPeople());
//                }
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(peoples ->
//                {
//                    mView.setView(peoples);
//                    Log.i("test", peoples.size() + "");
//                }, throwable ->
//                {
//                    ///throwable.printStackTrace();
//                    Log.e("test", throwable.getMessage());
//                });


        Disposable disposable2 =  Observable.create((ObservableOnSubscribe<List<People>>) emitter ->
        {
            List<People> list = model.getAllPeople();
            if (list == null || list.size() == 0)
            {
                emitter.onError(new Exception("没有数据"));
            } else
            {
                emitter.onNext(model.getAllPeople());
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(peoples ->
                {
                    mView.setView(peoples);
                    Log.i("test", peoples.size() + "");
                }, throwable ->
                {
                    ///throwable.printStackTrace();
                    Log.e("test", throwable.getMessage());
                });


   //     compositeDisposable.add(disposable);
        compositeDisposable.add(disposable2);


    }
}
