package com.ecg.process;

/**
 * 解析后数据映射实体
 *
 * @author huangfeng
 * @date 2019-06-27 08:59
 */
public class Result {

    int turnCount; //砖石次数
    int stepCount; //步数
    double metArr; //
    int bat; //电量
    int heartRate; //心率
    int abnState;
    int RR; //RR间期
    double RP;
    double motionIntensity; //当量
    float energyConsu;
    int index; //下标
    double totalEnergy; //能量
    double stepRate; //步频
    char motionState; //运动状态
    String motionStateDes; //运动状态描述

    public int getTurnCount() {
        return turnCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public double getMetArr() {
        return metArr;
    }

    public int getBat() {
        return bat;
    }

    public float getEnergyConsu() {
        return energyConsu;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getAbnState() {
        return abnState;
    }

    public int getRR() {
        return RR;
    }

    public double getRP() {
        return RP;
    }

    public double getMotionIntensity() {
        return motionIntensity;
    }

    public int getIndex() {
        return index;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public double getStepRate() {
        return stepRate;
    }

    public char getMotionState() {
        return motionState;
    }

    public String getMotionStateDes() {
        return motionStateDes;
    }
}
