package com.dream.mvpdemo.ui.activity.ble.devicedetail.adapter;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.dream.mvpdemo.R;
import java.util.List;

/**
 * Created by lyd10892 on 2016/8/23.
 */

public class HotelEntityAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {


    public List<BluetoothGattService> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;

    public HotelEntityAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(List<BluetoothGattService> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return allTagList.size() > 0?allTagList.size():0;
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).getCharacteristics().size();
        if (count >= 0 && !mBooleanMap.get(section)) {
            count = 0;
        }

        return allTagList.size() > 0 ? count : 0;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.hotel_title_item, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescHolder(mInflater.inflate(R.layout.hotel_desc_item, parent, false));
    }


    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
        holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? "展开" : "关闭";
                mBooleanMap.put(section, !isOpen);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });

       // holder.titleView.setText(allTagList.get(section).);
        holder.uuidView.setText(allTagList.get(section).getUuid().toString());
        holder.openView.setText(mBooleanMap.get(section) ? "关闭" : "展开");

    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescHolder holder, int section, int position) {
        int type = allTagList.get(section).getCharacteristics().get(position).getProperties();
        String t = "";
        Log.i("test","type = " + type);
        switch (type){
            case 0x01:t = "BROADCAST";
                break;
            case 0x02:t = "READ";
                break;
            case 0x04:t = "WRITE_NO_RESPONSE";
                break;
            case 0x08:t = "WRITE";
                break;
            case 0x10:t = "NOTIFY";
                break;
            case 0x20:t = "INDICATE";
                break;
            case 0x40:t = "SIGNED_WRITE";
                break;
            case 0x80:t = "EXTENDED_PROPS";
                break;
        }
        holder.descView.setText(t);

    }
}
