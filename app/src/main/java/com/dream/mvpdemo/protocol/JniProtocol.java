package com.dream.mvpdemo.protocol;

/******************************************************************
 * @文件名称 : JniProtocol
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/13 11:37
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/13
 ******************************************************************/
public class JniProtocol
{
    private static JniProtocol instance;

    public static JniProtocol getInstance()
    {
        if(instance == null)instance = new JniProtocol();
        return instance;
    }

    static {
        //System.loadLibrary("hf");

        System.loadLibrary("hf");
    }


    public native String stringFromJNI();

    public native int[] testArray(int[] test ,int length);

    public static String stringJNI = "这是原来的String";

}
