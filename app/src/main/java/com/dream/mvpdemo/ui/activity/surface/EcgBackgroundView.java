package com.dream.mvpdemo.ui.activity.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dream.mvpdemo.R;


/******************************************************************
 * @文件名称 : EcgBackgroundView
 * @文件作者 : 卢素强
 * @创建时间 : 2019/6/12 9:03
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/6/12
 ******************************************************************/
public class EcgBackgroundView extends View
{   final String TAG = "EcgBackgroundView";
    private static final int dialogSetECG = 1;
    private static final int dialogSetNIBP = 2;
    private static final int dialogSetGLU = 3;

    private static final int ONE_SCREEN_SFV_NUM = 4;
    private static final int SFV_SPACE = 2;
    private static final float BOX_SPACE = 0.5f;
    private static final float BOX_RECT_WIDTH = 1.5f;
    private static final float BOX_RECT_BOLD_LINE = 2.0f;
    private static final float BOX_RECT_NORMAL_LINE = 1.0f;
    private float mm_1;
    private float dpi;
    public EcgBackgroundView(Context context)
    {
        super(context);
        dpi = this.getResources().getDisplayMetrics().xdpi;
        mm_1 = this.MM2Pixel(1);
        setBackgroundColor(0xFFFFFF);
    }

    public EcgBackgroundView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        dpi = this.getResources().getDisplayMetrics().xdpi;
        mm_1 = this.MM2Pixel(1);
        setBackgroundColor(0xFFFFFF);
    }

    public EcgBackgroundView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        dpi = this.getResources().getDisplayMetrics().xdpi;
        mm_1 = this.MM2Pixel(1);
        setBackgroundColor(0xFFFFFF);
    }

    private float MM2Pixel(int milli) {
        return (float) (milli * dpi * 1.0f / 25.4f);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        Log.d(TAG, "w=" + w + ",h=" + h);
        Paint paint = new Paint();
        paint.setColor(0xFFFCF8FA);
//				paint.setColor(R.color.backView);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(BOX_RECT_WIDTH);
        canvas.drawRect(new RectF(0, 0, w, h), paint);

				/* draw box rect */
        paint.setColor(0xB8B7B7);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(BOX_SPACE, BOX_SPACE, w - BOX_SPACE,
                h - BOX_SPACE), paint);

        int maxLineW = (int) (w / mm_1);
        int maxLineH = (int) (h / mm_1);
        Log.d(TAG, "maxLineW=" + maxLineW + ",maxLineH=" + maxLineH);
				/* draw w line */
        paint.setColor(getResources().getColor(R.color.limegreen));
//				paint.setColor(R.color.redGridLine);
        for (int i = 1; i < maxLineH; i++) {
            if (i % 5 == 0) {
                paint.setStrokeWidth(BOX_RECT_BOLD_LINE);
                canvas.drawLine(BOX_SPACE + BOX_RECT_WIDTH/2, h - BOX_SPACE - i * mm_1
                                - BOX_RECT_BOLD_LINE / 2, w - BOX_SPACE- BOX_RECT_WIDTH/2,
                        h - BOX_SPACE - i * mm_1 - BOX_RECT_BOLD_LINE
                                / 2, paint);
            } else {
                paint.setStrokeWidth(BOX_RECT_NORMAL_LINE);
                canvas.drawLine(BOX_SPACE+ BOX_RECT_WIDTH/2, h - BOX_SPACE - i * mm_1
                        - BOX_RECT_NORMAL_LINE / 2, w - BOX_SPACE - BOX_SPACE- BOX_RECT_WIDTH/2, h
                        - BOX_SPACE - i * mm_1 - BOX_RECT_NORMAL_LINE
                        / 2, paint);
            }

        }
				/* draw h line */
        for (int i = 1; i < maxLineW; i++) {
            if (i % 5 == 0) {
                paint.setStrokeWidth(BOX_RECT_BOLD_LINE);
                canvas.drawLine(BOX_SPACE + i * mm_1
                        - BOX_RECT_BOLD_LINE / 2, BOX_SPACE + BOX_RECT_WIDTH/2, BOX_SPACE
                        + i * mm_1 - BOX_RECT_BOLD_LINE / 2, h
                        - BOX_SPACE - BOX_RECT_WIDTH/2, paint);
            } else {
                paint.setStrokeWidth(BOX_RECT_NORMAL_LINE);
                canvas.drawLine(
                        BOX_SPACE + i * mm_1 - BOX_RECT_NORMAL_LINE / 2,
                        BOX_SPACE + BOX_RECT_WIDTH/2,
                        BOX_SPACE + i * mm_1 - BOX_RECT_NORMAL_LINE / 2,
                        h - BOX_SPACE - BOX_RECT_WIDTH/2, paint);
            }
        }
    }
}
