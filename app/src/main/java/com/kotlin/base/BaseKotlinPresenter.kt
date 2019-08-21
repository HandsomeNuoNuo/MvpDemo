package com.kotlin.base

import android.util.Log
import com.dream.mvpdemo.base.IBaseModel
/******************************************************************
 * @文件名称 : BaseKotlinPresenter
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/5 15:39
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/5
 ******************************************************************/
abstract class BaseKotlinPresenter<V : IBaseKotlinView,M : IBaseModel>: IBaseKotlinPresenter<V> {
    protected var model: M? = null
    protected var mView: V? = null

    constructor(){
        Log.i("test","BaseKotlinPresenter constructor")
        initModel()
    }

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun disattachView(view: V) {
        mView = null
    }
}