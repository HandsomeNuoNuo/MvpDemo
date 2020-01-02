package com.javatest;

/**
 * 1（单）导 二代数据解析类
 * 数据格式为小端模式
 *
 * @author huangfeng
 * @date 2019-06-24 14:54
 */
public class _1C_V2_Converter {
    static int lastBat = 100;

    public static Data converter(byte[] buffer) {
        Data data = new Data();
        data.bat = buffer[0] & 0xff;//电量：一位int
        if (data.bat < lastBat) {
            lastBat = data.getBat();
        }
        data.bat = lastBat;
        //数据包下标：三位小端
        data.index = getLowInt(buffer[1], buffer[2], buffer[3]);
        //心电数据：24位，每2位小端表示一个数据
        double[] ecg = new double[12];
        int[] ecgOriginal = new int[12];
        int start = 4;
        for (int i = 0; i < ecg.length; i++) {
            int src = getLowInt(buffer[i * 2 + start], buffer[i * 2 + 1 + start]);
            ecgOriginal[i] = src;
            ecg[i] = src * 9d / 2047;
        }
        data.ecg_I = ecg;
        data.ecgOriginal = ecgOriginal;
        start = 28;
        //运动数据：36位，每2位小端表示一个数据 ，共三组，每组6个数据：(加速度x、y、z + 角速度x、y、z)
        int[] motion = new int[18];
        for (int i = 0; i < motion.length; i++) {
            int src = getLowInt(buffer[i * 2 + start], buffer[i * 2 + 1 + start]);
            motion[i] = src;
        }
        int[][] motionGroup = new int[3][6];

        for (int i = 0; i < motion.length; i++) {
            motionGroup[i / 6][i % 6] = motion[i];
        }
        data.motion = motionGroup;
        return data;
    }

   static int getLowInt(byte... bytes) {
        if (bytes.length == 1) {
            return bytes[0];
        }
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (i < bytes.length - 1) {
                result |= (bytes[i] & 0xff) << (8 * i);
            } else {
                result |= bytes[i] << (8 * i);
            }
        }
        return result;
    }
}
