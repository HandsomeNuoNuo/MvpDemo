package com.dream.mvpdemo.ui.activity.ble.devicedetail;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**********************************
 * @Name: BleService
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/24 12:37
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class BleService extends Service {
    private static BleService instance;
    private BluetoothGatt bluetoothGatt;
    private List<BleLinster> bleLinsters = new ArrayList<>();

    public static BleService getInstance(){
        return instance;
    }

    public void setBleLinsters(BleLinster bleLinster) {
        bleLinsters.add(bleLinster);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void connect(BluetoothDevice device ) {
        bluetoothGatt = device.connectGatt(this, false, bluetoothGattCallback);
    }

    public void disConnect() {
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }
    }

    BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            for (BleLinster bleLinster : bleLinsters) {
                bleLinster.onStatusChanged(newState);
            }
            if (newState == BluetoothProfile.STATE_CONNECTED && bluetoothGatt != null) {
                // 搜索GATT服务
                bluetoothGatt.discoverServices();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            Log.i("test", "onServicesDiscovered");
            for (BleLinster bleLinster : bleLinsters) {
                bleLinster.onServicesDiscovered(gatt);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            Log.i("test", "onCharacteristicRead");
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            Log.i("test", "onCharacteristicWrite");
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            Log.i("test", "onCharacteristicChanged");
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
            Log.i("test", "onDescriptorRead");
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            Log.i("test", "onDescriptorWrite");
        }
    };

}
