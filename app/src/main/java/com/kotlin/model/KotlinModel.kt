package com.kotlin.model

import com.dream.mvpdemo.model.bean.People
import com.dream.mvpdemo.model.db.DbHelper
import com.kotlin.contract.KotlinContract

/******************************************************************
 * @文件名称 : KotlinModel
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/7 9:07
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/7
 ******************************************************************/
class KotlinModel : KotlinContract.KotlinModel{

    override fun savePeople(name: String, age: Int, sex: String): Boolean {
        val people = People()
        people.name = name
        people.age = age
        people.sex = sex
        return DbHelper.getInstance().savePeople(people)
    }

    override fun getAllPeople(): List<People> {
        return DbHelper.getInstance().allPeople
    }

    fun getByID(id: Int): People? {

        return DbHelper.getInstance().getByID(id)
    }
}