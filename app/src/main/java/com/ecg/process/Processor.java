package com.ecg.process;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;


import com.javatest.Data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Processor {
    private Processor(int turnDistance, int height, int weight, int age, int sampleRate, int deviceVision) {
        this.turnDistance = turnDistance;
        mSignalProcessor = new SignalProcessor();
        //初始化设置顺序不能错
        mSignalProcessorV2 = new SignalProcessorV2();
        mSignalProcessorV2.createObject(false, turnDistance, sampleRate, 0);
        mSignalProcessorV2.setPersonInfo(height, weight);
        mSignalProcessorV2.reset();
        mSignalProcessorV2.setAge(age);
        mSignalProcessorV2.setEquipInfo(deviceVision);
        mSignalProcessorV2.setTurnDistance(turnDistance);
        mSignalProcessorV2.reset();

    }

    public static class Builder {
        @IntDef(value = {_1C_V1, _1C_V2})
        @Retention(RetentionPolicy.SOURCE)
        private @interface DeviceVision {
        }

        int turnDistance = 30;
        int height = 170;
        int weight = 65;
        int age = 25;
        //运动数据的采样频率
        int sampleRate = 50;

        int deviceVision = _1C_V2;

        public Builder turnDistance(@IntRange(from = 5, to = 100) int turnDistance) {
            this.turnDistance = turnDistance;
            return this;
        }

        public Builder height(@IntRange(from = 50, to = 220) int height) {
            this.height = height;
            return this;
        }

        public Builder weight(@IntRange(from = 30, to = 150) int weight) {
            this.weight = weight;
            return this;
        }

        public Builder age(@IntRange(from = 0, to = 120) int age) {
            this.age = age;
            return this;
        }

        @Deprecated
        public Builder sampleRate(int sampleRate) {
            this.sampleRate = sampleRate;
            return this;
        }

        @Deprecated
        public Builder deviceVision(@DeviceVision int deviceVision) {
            this.deviceVision = deviceVision;
            return this;
        }

        public Processor build() {
            return new Processor(turnDistance, height, weight, age, sampleRate, deviceVision);
        }
    }


    private SignalProcessorV2 mSignalProcessorV2;
    private SignalProcessor mSignalProcessor;


    public void startWalk() {
        reset();
        mSignalProcessorV2.startWalk();
    }

    public void reset() {
        mCurrentIndex = 0;
        motionCalcTimes = 0;
        mSignalProcessorV2.reset();
    }

    public static final int _1C_V1 = 0;
    public static final int _1C_V2 = 1;

    /**
     * 设置判断设备类型
     * SDK默认为新单导
     *
     * @param ecgEquipType 单导设备类型 0--旧单导设备 1--新单导设备
     */
    @Deprecated
    public void setEquipInfo(int ecgEquipType) {
        if (mSignalProcessorV2 != null) {
            mSignalProcessorV2.setEquipInfo(ecgEquipType);
        }
    }

    public void destroy() {
        if (mSignalProcessor != null) {
            mSignalProcessor.recycle();
            mSignalProcessor = null;
        }
        if (mSignalProcessorV2 != null) {
            mSignalProcessorV2.recycle();
            mSignalProcessorV2 = null;
        }
    }


    public float getStepPerTurn(int turns, float step_count_total) {
        float step_per_turn = 0.0f;
        if (turns > 0) {
            step_per_turn = step_count_total / turns;
        } else {
            //step_per_turn = step_count_total;
        }
        return step_per_turn;
    }

    private int turnDistance;

    public float getDistance(int turns, float step_count_last, float step_per_turn) {
        float result = 0.0f;
        if (turns > 0) {
            float rate = step_count_last / step_per_turn;
            result = (turns + rate) * turnDistance;
        }
        return result;
    }

    int mCurrentIndex = 0;
    int lastRR = 0;
    float lastRP = 0;
    /**
     * 运动数据计算的次数
     */
    private int motionCalcTimes = 0;
    /**
     * 缓存包数据，用于V2算法计算
     */
    private List<int[]> motionCacheList = new ArrayList<>();

    public void processor(Data data) {
        mCurrentIndex++;
        Result result = new Result();
        result.index = mCurrentIndex;
        result.bat = data.getBat();

        motionCacheList.addAll(Arrays.asList(data.getMotion()));
        int[] ecgOriginData = new int[64];
        for (int i = 0; i < data.getEcg_I().length; i++) {
            ecgOriginData[i] = (int) (data.getEcg_I()[i] / 0.0016d) + 2048;
        }
        int[] accOriginData = new int[48];
        int[] gyrX = new int[16];//陀螺仪x轴原始数据
        int[] gyrY = new int[16];//陀螺仪y轴原始数据
        int[] gyrZ = new int[16];//陀螺仪z轴原始数据
        for (int i = 0; i < data.getMotion().length; i++) {
            int[] accData = data.getMotion()[i];
            accOriginData[i] = accData[3];
            accOriginData[i + 16] = accData[4];
            accOriginData[i + 32] = accData[5];
            gyrX[i] = accData[0];
            gyrY[i] = accData[1];
            gyrZ[i] = accData[2];
        }
        int[] heartRateParams = new int[3];
        double[] motionParams = new double[6];
        try {
            mSignalProcessor.ecgProcessing(ecgOriginData, data.getEcgOriginal().length, heartRateParams);
            mSignalProcessor.accProcessing(accOriginData, accOriginData.length, motionParams);
            mSignalProcessor.gayProcessing(gyrX, gyrY, gyrZ, gyrX.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.heartRate = heartRateParams[0];
        result.abnState = heartRateParams[2];
        result.motionIntensity = motionParams[3];
        result.energyConsu = (float) motionParams[4];
        int curRR = (int) (1000f * heartRateParams[1] / 200);
        if (curRR > 0) {
            if (lastRR != 0) {
                lastRP = (curRR - lastRR) * 100f / lastRR;
                if (lastRP > 100) {
                    lastRP = 100;
                } else if (lastRP < -100) {
                    lastRP = -100;
                }
            }

            lastRR = curRR;
        }
        result.RR = lastRR;
        result.RP = lastRP;

        if (motionCacheList.size() >= 64) {
//                        陈浩（V2）算法计算运动数据
            int[] indexPos = new int[64];
            double[] AccXData = new double[64];
            double[] AccYData = new double[64];
            double[] AccZData = new double[64];
            double[] GryXData = new double[64];
            double[] GryYData = new double[64];
            double[] GryZData = new double[64];
            for (int i = 0; i < 64; i++) {
                int[] accData = motionCacheList.get(0);
                indexPos[i] = i + motionCalcTimes * 64;
                int index = i;
                //加速度x、y、z + 角速度x、y、z
                AccXData[index] = accData[3] / 32;
                AccYData[index] = accData[4] / 32f;
                AccZData[index] = accData[5] / 32f;
                GryXData[index] = accData[0];
                GryYData[index] = accData[1];
                GryZData[index] = accData[2];
                motionCacheList.remove(0);
            }
            mSignalProcessorV2.calcMetsForOut(indexPos, AccXData, AccYData, AccZData, GryXData, GryYData, GryZData, indexPos.length);
            motionCalcTimes++;
            result.metArr = mSignalProcessorV2.getMetsAttr();

//            result.motionIntensity = (char) mSignalProcessorV2.getMinuteMets();
//            if (result.motionIntensity < 0) {
//                result.motionIntensity = 1;
//            }
            result.motionState = (char) mSignalProcessorV2.getMotionstate();
            result.motionStateDes = mSignalProcessorV2.getMotionStateToString(mSignalProcessorV2.getMotionstate());
//                    步频  步/分钟
            result.stepRate = mSignalProcessorV2.getStepFrequence();
//                    总步数
            result.stepCount = mSignalProcessorV2.getTotalStep();
            result.turnCount = mSignalProcessorV2.getTurnResultCount();
            result.totalEnergy = mSignalProcessorV2.getTotalEnergy();
            //callback.result(result);
        }
    }


}
