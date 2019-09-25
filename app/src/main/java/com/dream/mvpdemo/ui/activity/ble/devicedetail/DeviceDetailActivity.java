package com.dream.mvpdemo.ui.activity.ble.devicedetail;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.ui.activity.ble.devicedetail.adapter.HotelEntityAdapter;

import butterknife.BindView;

/**********************************
 * @Name: DeviceDetailActivity
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 20:57
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class DeviceDetailActivity extends BaseActivity<DeviceDetailPresenter> implements DeviceDetailContract.View {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.listview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_status)
    TextView tv_status;
    private BluetoothDevice device;
    private HotelEntityAdapter mAdapter;

    Handler handler = new Handler();

    @Override
    protected void initPresenter() {
        mPresenter = new DeviceDetailPresenter();
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        device = intent.getParcelableExtra("DEVICE");
        setViewTitle();
        mPresenter.connect(device, mContext);

        mAdapter = new HotelEntityAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void setViewTitle() {
        toolbar.setTitle(device.getName() == null ? "No Name" : device.getName());
        tvAddress.setText(device.getAddress());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bledetail;
    }

    @Override
    public void setConnectStatus(int status) {
        handler.post(() -> {
            switch (status){
                case BluetoothProfile.STATE_DISCONNECTED:
                    tv_status.setText("未连接");
                    tv_status.setTextColor(Color.RED);
                    break;
                case BluetoothProfile.STATE_CONNECTING:
                    tv_status.setTextColor(Color.BLUE);
                    tv_status.setText("连接中");
                    break;
                case BluetoothProfile.STATE_CONNECTED:
                    tv_status.setTextColor(Color.GREEN);
                    tv_status.setText("已连接");
                    break;
            }
        });

    }

    @Override
    public void setData(BluetoothGatt gatt) {
        handler.post(() -> {
            mAdapter.setData(gatt.getServices());
            recyclerView.setAdapter(mAdapter);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disConnect();
    }
}
