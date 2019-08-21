package com.kotlin.base

/******************************************************************
 * @文件名称 : IBaseKotlinPresenter
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/5 15:41
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/5
 ******************************************************************/
interface IBaseKotlinPresenter<V : IBaseKotlinView> {
    fun initModel()
    fun attachView(view: V)
    fun disattachView(view: V)
}