package com.dream.mvpdemo.ui.activity.ble.scan;

import android.bluetooth.BluetoothDevice;

import com.dream.mvpdemo.base.IBaseModel;
import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;

import java.util.List;

/**********************************
 * @Name: BleListContract
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 15:17
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public interface BleListContract {
    interface Model extends IBaseModel
    {
    }


    interface View extends IBaseView
    {
        void setList(List<BluetoothDevice> devices);
        void addOneDevice(BluetoothDevice devices);
    }

    interface Presenter extends IBasePresenter<View>
    {
        void checkBleOpened();
        void startScan();
        void stopScan();
    }
}
