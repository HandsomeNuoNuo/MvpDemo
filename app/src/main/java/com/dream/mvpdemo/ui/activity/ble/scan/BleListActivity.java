package com.dream.mvpdemo.ui.activity.ble.scan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.ui.activity.ble.devicedetail.BleService;
import com.dream.mvpdemo.ui.activity.ble.devicedetail.DeviceDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**********************************
 * @Name: BleListActivity
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 15:12
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
@RuntimePermissions
public class BleListActivity extends BaseActivity<BleListPresenter> implements BleListContract.View {
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.listview)
    RecyclerView recyclerView;

    private boolean isScan = false;
    private DevicesAdapter mAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new BleListPresenter();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        toolbar.setTitle("蓝牙调试");
        BleListActivityPermissionsDispatcher.getPremissionWithPermissionCheck(this);
        mPresenter.checkBleOpened();
        startService(new Intent(mContext, BleService.class));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DevicesAdapter();
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(isScan){
                    showSnackbar("请先停止扫描");
                    return;
                }
                Intent intent =new Intent(mContext, DeviceDetailActivity.class);
                intent.putExtra("DEVICE",(BluetoothDevice)adapter.getData().get(position));
                startActivity(intent);
            }
        });
        mAdapter.bindToRecyclerView(recyclerView);
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN})
    public void getPremission()
    {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_blelist;
    }

    @OnClick(R.id.btn_scan)
    public void onViewClicked() {
        if(!isScan){
            isScan = true;
            mPresenter.startScan();
            btnScan.setText("停止扫描");
        }else {
            isScan = false;
            mPresenter.stopScan();
            btnScan.setText("开始扫描");
        }
    }

    @Override
    public void setList(List<BluetoothDevice> devices) {
    }

    @Override
    public void addOneDevice(BluetoothDevice devices) {
        mAdapter.addOneData(devices);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(mContext, BleService.class));
    }
}
