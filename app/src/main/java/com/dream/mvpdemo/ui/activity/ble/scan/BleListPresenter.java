package com.dream.mvpdemo.ui.activity.ble.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

import com.dream.mvpdemo.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**********************************
 * @Name: BleListPresenter
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 15:13
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class BleListPresenter extends BasePresenter<BleListContract.View, BleListContract.Model> implements BleListContract.Presenter{
   private BluetoothAdapter bluetoothAdapter;
   private BluetoothLeScanner scanner;
   private List<BluetoothDevice> devices = new ArrayList<>();

    @Override
    public void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        scanner = bluetoothAdapter.getBluetoothLeScanner();
    }

    @Override
    public void initModel() {

    }

    ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            BluetoothDevice device = result.getDevice();
            if (!devices.contains(device)) {  //判断是否已经添加
                devices.add(device);
                mView.addOneDevice(device);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };

    /**
     * 此处应采用动态请求蓝牙回调的方式
     */
    @Override
    public void checkBleOpened() {
        if(!bluetoothAdapter.isEnabled()){
            bluetoothAdapter.enable();
        }
    }

    @Override
    public void startScan() {
        scanner.startScan(scanCallback);
    }

    @Override
    public void stopScan() {
        scanner.stopScan(scanCallback);
    }
}
