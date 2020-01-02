package com.dream.mvpdemo.ui.activity.ble.devicedetail;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;

import com.dream.mvpdemo.base.IBaseModel;
import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;

/**********************************
 * @Name: DeviceDetailContract
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 20:58
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public interface DeviceDetailContract {
    interface Model extends IBaseModel{

    }

    interface View extends IBaseView{
        void setConnectStatus(int status);
        void setData(BluetoothGatt gatt);
    }

    interface Presenter extends IBasePresenter<View>{
        void connect(BluetoothDevice device, Context context);
        void disConnect();

    }
}