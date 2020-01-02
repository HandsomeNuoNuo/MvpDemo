
package com.ecg.process;


import android.util.Log;

/**
 * 信号处理器
 * 20160717，心电采样200HZ，加速度50HZ
 *
 * @author ligang
 */
class SignalProcessor {

    static {
        try {
            System.loadLibrary("cyyl");
            //System.loadLibrary("cyyl-20170802");
            //System.loadLibrary("cyyl-2017-09-05");
//			Log.i("JNI", "SignalProcessor libucareRG.so");
        } catch (UnsatisfiedLinkError ule) {
            Log.e("JNI", "WARNING: Could not load libcyyl.so");
        }
    }


    public SignalProcessor() {
        createObject();
    }

    public void recycle() {

        deleteObject();

    }

    public void clear() {

        recycle();
        createObject();
    }

    /**
     * 创建对象，并返回句柄
     *
     * @return
     */
    public native void createObject();

    /**
     * 删除处理器对象
     *
     * @param pEcgObject
     */
    public native void deleteObject();

    /***
     * 心电分析处理
     *
     * @param ecgData:心电数据，数组长度64
     * @param ecgleng:心电数据的长度
     * @param heartRate:返回的分析结果，数组长度为3，
     *                    heartRate[0]：表示心率
     *                    heartRate[1]：表示RR间期
     *                    heartRate[2]：表示异常类型
     */
    public native boolean ecgProcessing(int[] dataEcg, int ecgleng, int[] heartrate);

    /***
     * 加速度分析处理
     *
     * @param accData:加速度数据，数组长度48
     * @param accLength:加速度数据的长度
     * @param activity:返回的分析结果，数组长度为6，
     *                   activity[0]：表示运动状态
     *                   activity[1]：表示步频
     *                   activity[2]：表示加速度抖动强度
     *                   activity[3]：表示MET
     *                   activity[4]：表示能量消耗
     *                   activity[5]：表示步数
     */
    public native boolean accProcessing(int[] dataAcc, int accLength,
                                        double[] activity);

    /***
     * 旋转分析处理
     *
     * @param gayx:陀螺仪x轴数据，数组长度16
     * @param gayy:陀螺仪y轴数据，数组长度16
     * @param gayz:陀螺仪z轴数据，数组长度16
     * @param gayLength:每轴陀螺仪数据的长度
     *  return：返回值1表示选择，0表示未旋转
     */
    public native int gayProcessing(int[] gayx, int[] gayy, int[] gayz, int gaylength);


    public void processData(
            int[] dataEcg, int ecgLength,
            int[] dataAcc, int accLength,
            int[] heartrate,
            double[] activity) {
        this.ecgProcessing(dataEcg, ecgLength, heartrate);
        this.accProcessing(dataAcc, accLength, activity);
    }

    private String TAG = "SingalProcessr";

    public int processData(
            int[] dataEcg, int ecgLength, int[] dataAcc, int accLength, int[] gayx, int[] gayy, int[] gayz, int palLength, int[] heartrate,
            double[] activity) {
        this.ecgProcessing(dataEcg, ecgLength, heartrate);
        Log.d(TAG, "processData: heartrate[1]:" + heartrate[1] + "  heartrate[2]:" + heartrate[2]);
        this.accProcessing(dataAcc, accLength, activity);
        return this.gayProcessing(gayx, gayy, gayz, gayx.length);
    }

}
