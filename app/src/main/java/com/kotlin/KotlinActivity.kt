package com.kotlin

import android.util.Log
import android.view.View
import android.widget.Button
import com.dream.mvpdemo.R
import com.dream.mvpdemo.model.bean.People
import com.kotlin.base.BaseKotlinActivity
import com.kotlin.base.IBaseKotlinPresenter
import com.kotlin.base.IBaseKotlinView
import com.kotlin.contract.KotlinContract
import com.kotlin.presenter.KotlinPresenter

class KotlinActivity : BaseKotlinActivity(),KotlinContract.View {

    var btn_1 : Button? = null
    var presenter :KotlinPresenter? = null

    override fun initView() {
        toolbar!!.setTitle("Kotlin")
        btn_1 = findViewById(R.id.btn_1)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun setClickLinster() {
        btn_1!!.setOnClickListener(View.OnClickListener {
            presenter!!.getAllPeople()
        })
    }

    override fun initPresenter() {
        Log.i("test","initPresenter ")
        presenter = KotlinPresenter()
        mPresenter = presenter!! as IBaseKotlinPresenter<IBaseKotlinView>
    }

    override fun saveOK() {
    }

    override fun saveFail() {
    }

    override fun setView(list: List<People>) {
    }

}
