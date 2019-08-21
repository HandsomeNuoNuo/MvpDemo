package com.kotlin.presenter

import android.util.Log
import com.dream.mvpdemo.model.bean.People
import com.kotlin.base.BaseKotlinPresenter
import com.kotlin.contract.KotlinContract
import com.kotlin.model.KotlinModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/******************************************************************
 * @文件名称 : KotlinPresenter
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/6 8:47
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/6
 ******************************************************************/
class KotlinPresenter : BaseKotlinPresenter<KotlinContract.View, KotlinContract.KotlinModel>(), KotlinContract.Presenter {

    override fun initModel() {
        Log.i("test","initModel ")
        model = KotlinModel()
    }
    override fun getAllPeople() {
        Observable.create(ObservableOnSubscribe<List<People>> { emitter ->

            Log.i("test","model = " + model)
            val list = model!!.getAllPeople()
            if (list.size == 0) {
                emitter.onError(Exception("没有数据"))
            } else {
                emitter.onNext(model!!.getAllPeople())
            }
            emitter.onComplete()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ peoples ->
                    mView!!.setView(peoples)
                    Log.i("test", peoples.size.toString() + "")
                }) { throwable ->
                    ///throwable.printStackTrace();
                    Log.e("test", throwable.message)
                }


    }

    override fun savePeople(name: String, age: Int, sex: String) {
    }


}