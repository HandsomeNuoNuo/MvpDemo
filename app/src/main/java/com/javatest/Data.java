package com.javatest;

import java.util.Arrays;

/**
 * 解析后数据映射实体
 *
 * @author huangfeng
 * @date 2019-06-27 08:59
 */
public class Data{
    int bat;
    int index;
    double[] ecg_I;
    int[] ecgOriginal;
    /**
     * double[3][6]
     * 共三组，每组6个数据：(加速度x、y、z + 角速度x、y、z)
     *
     * @author huangfeng
     * @date 2019-06-27 17:20
     */
    int[][] motion;


    public double[] getEcg_I() {
        return ecg_I;
    }

    public void setEcg_I(double[] ecg_I) {
        this.ecg_I = ecg_I;
    }

    public int getBat() {
        return bat;
    }

    public void setBat(int bat) {
        this.bat = bat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[] getEcgOriginal() {
        return ecgOriginal;
    }

    public void setEcgOriginal(int[] ecgOriginal) {
        this.ecgOriginal = ecgOriginal;
    }

    public int[][] getMotion() {
        return motion;
    }

    public void setMotion(int[][] motion) {
        this.motion = motion;
    }

    @Override
    public String toString() {
        return "Data:" +
                "bat=" + bat +
                ", index=" + index +
                ", ecg=" + Arrays.toString(ecg_I) +
                ", motion=" + Arrays.toString(motion) +
                '\n';
    }
}
