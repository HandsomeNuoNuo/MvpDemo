package com.dream.mvpdemo.ui.activity.ble.devicedetail;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;

import java.util.List;

/**********************************
 * @Name: DeviceDetailPresenter
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 21:02
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class DeviceDetailPresenter extends BasePresenter<DeviceDetailContract.View, DeviceDetailContract.Model> implements DeviceDetailContract.Presenter, BleLinster {

    private BluetoothGatt bluetoothGatt;

    @Override
    public void init() {

    }

    @Override
    public void initModel() {

    }

    @Override
    public void connect(BluetoothDevice device, Context context) {
        mView.setConnectStatus(1);
        BleService.getInstance().setBleLinsters(this);
        BleService.getInstance().connect(device);
    }

    @Override
    public void disConnect() {
        BleService.getInstance().disConnect();
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt) {
        mView.setData(gatt);
    }

    @Override
    public void onStatusChanged(int status) {
        mView.setConnectStatus(status);
    }
}
