package com.dream.mvpdemo.ui.activity.ble.devicedetail;

import android.bluetooth.BluetoothGatt;

/**********************************
 * @Name: BleLinster
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/24 12:51
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public interface BleLinster {

    void onStatusChanged(int status);
    void onServicesDiscovered(BluetoothGatt gatt);
}
