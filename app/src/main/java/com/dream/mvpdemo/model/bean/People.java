package com.dream.mvpdemo.model.bean;

import org.litepal.crud.LitePalSupport;

/******************************************************************
 * @文件名称 : People
 * @文件作者 : 黄峰
 * @创建时间 : 2019/7/19 8:55
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/7/19
 ******************************************************************/
public class People extends LitePalSupport
{
    private String name;
    private int age;
    private String sex;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }
}
