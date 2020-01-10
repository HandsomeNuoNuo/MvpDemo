package com.dream.mvpdemo.ui.activity.share;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**********************************
 * @Name: ShareActivity
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/10/5 20:08
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class ShareActivity extends BaseActivity {
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }


    @OnClick(R.id.btn_share)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                String path =  Environment.getExternalStorageDirectory().getAbsolutePath();
                Log.i("test","path = " +path);
                getScrollViewBitmap(scrollView,path+"/123.png");
                break;
        }
    }

    /**
     * 截取scrollview的屏幕
     **/
    public static Bitmap getScrollViewBitmap(ScrollView scrollView, String picpath) {
        int h = 0;
        Bitmap bitmap;
        // 获取listView实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        Log.d("test", "实际高度:" + h);
        Log.d("test", " 高度:" + scrollView.getHeight());
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(picpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
        }
        return bitmap;
    }

}
