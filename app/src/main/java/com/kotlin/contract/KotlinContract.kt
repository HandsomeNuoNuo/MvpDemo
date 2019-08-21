package com.kotlin.contract

import com.dream.mvpdemo.base.IBaseModel
import com.dream.mvpdemo.model.bean.People
import com.kotlin.base.IBaseKotlinPresenter
import com.kotlin.base.IBaseKotlinView

/******************************************************************
 * @文件名称 : KotlinContract
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/6 8:51
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/6
 ******************************************************************/
interface KotlinContract {
    interface KotlinModel : IBaseModel {
        fun getAllPeople(): List<People>
        fun savePeople(name: String, age: Int, sex: String): Boolean
    }


    interface View : IBaseKotlinView {
        fun saveOK()
        fun saveFail()
        fun setView(list: List<People>)
    }

    interface Presenter : IBaseKotlinPresenter<View> {
        fun savePeople(name: String, age: Int, sex: String)
        fun getAllPeople()
    }
}