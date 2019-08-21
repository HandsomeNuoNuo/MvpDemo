package com.dream.mvpdemo;

import org.litepal.LitePalApplication;

/******************************************************************
 * @文件名称 : MyApplication
 * @文件作者 : 黄峰
 * @创建时间 : 2019/7/19 8:57
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/7/19
 ******************************************************************/

public class MyApplication extends LitePalApplication
{
    private static MyApplication instance;

    private final String TAG="MyApplication";

    public static MyApplication getInstance()
    {
        if (instance == null) instance = new MyApplication();
        return instance;
    }
}
