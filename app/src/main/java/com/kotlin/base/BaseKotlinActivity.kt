package com.kotlin.base

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.dream.mvpdemo.R

/******************************************************************
 * @文件名称 : BaseKotlinActivity
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/5 14:44
 * @文件描述 :
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/5
 ******************************************************************/
abstract class BaseKotlinActivity: AppCompatActivity(),IBaseKotlinView {
    var mPresenter : IBaseKotlinPresenter<IBaseKotlinView>?= null
    var mContext : Context? = null
    var toolbar: Toolbar? =null
    var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        toolbar = this.findViewById(R.id.kot_toolbar)
        toolbar!!.setTitle("")
        setSupportActionBar(toolbar)
        actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        mContext = this
        initPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this)
        }
        initView()
        setClickLinster()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        mPresenter?.disattachView(this)
        super.onDestroy()
    }

    abstract fun getLayoutId(): Int
    abstract fun initPresenter()
    abstract fun initView()
    abstract fun setClickLinster()

    fun showToast(s :String){
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(s :String){
        Snackbar.make(toolbar!!,s,Snackbar.LENGTH_SHORT).show()
    }

}