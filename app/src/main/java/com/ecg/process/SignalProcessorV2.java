package com.ecg.process;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHao on 2018/9/27.
 * E-Mail: wh_main@163.com
 * Description:信号处理器 V2版
 */
class SignalProcessorV2 {
    static {
        try {
            System.loadLibrary("cyyl2");
        } catch (UnsatisfiedLinkError ule) {
            Log.e("JNI", "WARNING: Could not load libcyyl2.so");
        }
    }


    public void recycle() {

        deleteObject();

    }

    /**
     * @param enableStepReCal 是否启用步长修正，6分钟可以开启，提高精度
     * @param turnDistance    转身距离
     * @param sampleRate      运动数据采样频率
     */
    public native void createObject(boolean enableStepReCal, double turnDistance, int sampleRate, double fixStepDistance);

    public native void deleteObject();

    /**
     * 设置个人信息
     *
     * @param height 身高cm
     * @param weight 体重
     */
    public native void setPersonInfo(int height, int weight);

    /**
     * 设置年龄
     * age 年龄
     */
    public native void setAge(int age);

    /**
     * 设置判断设备类型
     *
     * @param ecgEquipType 单导设备类型 0--旧单导设备 1--新单导设备
     */
    public native void setEquipInfo(int ecgEquipType);

    /**
     * 重置
     */
    public native void reset();

    /**
     * 开始步行试验
     */
    public native void startWalk();

    /**
     * 结束步行试验
     */
    public native void endWalk();

    /**
     * 计算代谢当量处理逻辑，包括步频，步长等
     *
     * @return
     */
    public native void calcMets(int[] indexPos, double[] AccXData, double[] AccYData, double[] AccZData, double[] GryXData, double[] GryYData, double[] GryZData, int count);

    /**
     * 获取状态字符串
     *
     * @param motionstate
     * @return
     */
    public native String getMotionStateToString(int motionstate);

    /**
     * 设置步长
     *
     * @param value 步长
     */
    public native void setStepDistance(double value);


    /**
     * 获取步长
     *
     * @return
     */
    public native double getStepDistance();


    /**
     * 获取每分钟的数据
     *
     * @param distance
     * @param turnCount
     * @param step
     * @param speed
     * @param mets
     * @return
     */
    public native int getMinuteResult(double[] distance, int[] turnCount, int[] step, double[] speed, double[] mets);

    /**
     * 获取转身次数
     *
     * @return
     */
    public native int getTurnResultCount();


    /**
     * 设置转身距离
     *
     * @param turnDistance 距离
     */
    public native void setTurnDistance(double turnDistance);
//    *********************************以下为获取MotionAnalyser.h中的属性的方法***********************************

    /**
     * 当前方向
     *
     * @return
     */
    public native double getCurrentDirection();

    /**
     * 获取步频
     *
     * @return
     */
    public native double getStepFrequence();

    /**
     * 身高cm
     *
     * @return
     */
    public native int getPersonHeight();

    /**
     * 体重kg
     *
     * @return
     */
    public native int getPersonWeight();

    /**
     * 获取步速(m/s)
     *
     * @return
     */
    public native double getStepVelocity();

    /**
     * 获取总步数
     *
     * @return
     */
    public native int getTotalStep();

    /**
     * 获取属性中瞬时代谢当量
     *
     * @return
     */
    public native double getMetsAttr();

    /**
     * 总能量(kcal)
     *
     * @return
     */
    public native double getTotalEnergy();

    /**
     * 运动姿态 1-步行 2-站/坐 3-跑步 4-趟 其他
     *
     * @return
     */
    public native int getMotionstate();

    /**
     * 获取平均步长
     *
     * @return 步长
     */
    public native double getAvgStepDistance();

    private List<Integer> mTempIndexPostList = new ArrayList<>();
    private List<Integer> mTempSteps = new ArrayList<>();
    private int mLastTotalStep = 0;
    private int mSampleRate = 200;

    /**
     * 计算代谢当量
     * speed:速度(m/s)
     * grade:坡度(grade in decimal form (5% is 0.05), if 0% grade, no vert.) 默认为0
     */
    private native double getMets(double speed, double grade);

    /**
     * 计算Mets值
     */
    public synchronized void calcMetsForOut(int[] indexPos, double[] AccXData, double[] AccYData, double[] AccZData, double[] GryXData, double[] GryYData, double[] GryZData, int count) {
//           添加到本地缓存
        if (mTempIndexPostList.size() < mSampleRate * 60) {
            for (int i = 0; i < indexPos.length; i++) {
                mTempIndexPostList.add(indexPos[i]);
            }
        }
//        调用算法 计算mets值
        calcMets(indexPos, AccXData, AccYData, AccZData, GryXData, GryYData, GryZData, count);

        if (mTempIndexPostList.size() >= mSampleRate * 60) {
            mTempSteps.remove(0);
        }
//        添加步数数据
        int step = getTotalStep() - mLastTotalStep;
        mTempSteps.add(step);

        mLastTotalStep = getTotalStep();


    }


    /**
     * 获取每分钟的mets值 在没有一分钟数据的时候 分钟的mets为 -1
     *
     * @return
     */
    public synchronized double getMinuteMets() {
        if (mTempIndexPostList.size() < mSampleRate * 60) {
            return -1;
        }
//        获取平均步长
        double avgStepDistance = getAvgStepDistance();
//        计算一分钟总步数
        int sumSteps = 0;
        for (int i = 0; i < mTempSteps.size(); i++) {
            sumSteps += mTempSteps.get(i);
        }
//        计算距离
        double distance = avgStepDistance * sumSteps / 60.0d;
        return getMets(distance, 0);
    }

    /**
     * 重新开始统计Mets值
     */
    public void reStatisticsMets() {
        mTempIndexPostList.clear();
        mTempSteps.clear();
    }
}
