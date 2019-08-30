package com.hf.mylibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/******************************************************************
 * @文件名称 : SDKTest
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/30 11:12
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/30
 ******************************************************************/
public class SDKTest
{
    private static final String TAG="SDKTest";

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    /**
     * 读取application 节点  meta-data 信息
     */
    public static String readMetaDataFromApplication() {
        String sdkTest = "";
        try {
            ApplicationInfo appInfo = mContext.getPackageManager()
                    .getApplicationInfo(mContext.getPackageName(),
                            PackageManager.GET_META_DATA);
            sdkTest = appInfo.metaData.getString("SDKTest");

            Log.e(TAG, "SDKTest=" + sdkTest);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return sdkTest;
    }
}
