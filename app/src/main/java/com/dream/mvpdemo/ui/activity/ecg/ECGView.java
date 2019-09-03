package com.dream.mvpdemo.ui.activity.ecg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dream.mvpdemo.R;

import java.util.concurrent.ConcurrentLinkedQueue;

/******************************************************************
 * @文件名称 : ECGView
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/29 8:47
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/29
 ******************************************************************/
public class ECGView extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private final String TAG = "ECGView";

    private volatile boolean isDrawing = false;

    private Canvas canvas;

    private SurfaceHolder surfaceHolder;

    private Path path;

    private int width, height;

    private static final float BOX_SPACE = 0.5f;

    private static final float BOX_RECT_WIDTH = 1.5f;

    private static final float BOX_RECT_BOLD_LINE = 2.0f;

    private static final float BOX_RECT_NORMAL_LINE = 1.0f;

    private float mm_1;

    private float dpi;

    private int maxLineH,maxLineW;
    private Paint gridPaint,backPaint, mPaint;

    private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();

    public ECGView(Context context)
    {
        super(context);
        init();
    }

    public ECGView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public ECGView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        Log.i(TAG, "init");
        this.setZOrderMediaOverlay(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        dpi = this.getResources().getDisplayMetrics().xdpi;
        mm_1 = this.MM2Pixel(1);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
    }

    public void pushData(int data)
    {
        queue.add(data);
    }

    private float MM2Pixel(int milli)
    {
        return (float) (milli * dpi * 1.0f / 25.4f);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        Log.i(TAG, "surfaceCreated");
        initPaint();
        isDrawing = true;
        new Thread(this).start();
    }

    private void initPaint(){

        try
        {
            canvas = surfaceHolder.lockCanvas();
            width = canvas.getWidth();
            height = canvas.getHeight();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (canvas != null)
            {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        maxLineW = (int) (width / mm_1);
        maxLineH = (int) (height / mm_1);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        path = new Path();

         gridPaint = new Paint();
         backPaint = new Paint();
        backPaint.setColor(0xFFFCF8FA);
        backPaint.setStyle(Paint.Style.FILL);
        backPaint.setStrokeWidth(BOX_RECT_WIDTH);

        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setColor(getResources().getColor(R.color.limegreen));
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        Log.i(TAG, "surfaceChanged");
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        Log.i(TAG, "surfaceDestroyed");
        isDrawing = false;
        surfaceHolder.removeCallback(this);
    }

    @Override
    public void run()
    {
        resetView();
        int x = 0;
        Log.i(TAG, "draw");
        while (isDrawing)
        {     //此处容易阻塞
            if (x > width)
            {
                resetView();
                x = 0;
            } else
            {
               // Log.i(TAG,"queue.size() =" + queue.size());
                if (queue.size() > 0)
                {
                    draw(x, queue.poll());
                    x += 10;
                }
            }
        }
    }

    private void draw(int x, int y)
    {
        try
        {
            canvas = surfaceHolder.lockCanvas();
            //执行具体的绘制操作
            drawGrid(canvas);
           // Log.i(TAG, " x = " + x);
            path.lineTo(x, height / 2 - y);
            canvas.drawPath(path, mPaint);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (canvas != null)
            {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private void resetView()
    {
        path.moveTo(0, height / 2);
    }


    private void drawGrid(Canvas canvas)
    {
        canvas.drawRect(new RectF(0, 0, width, height), backPaint);
//				paint.setColor(R.color.redGridLine);
        for (int i = 1; i < maxLineH; i++)
        {
            if (i % 5 == 0)
            {
                gridPaint.setStrokeWidth(BOX_RECT_BOLD_LINE);
                canvas.drawLine(BOX_SPACE + BOX_RECT_WIDTH / 2, height - BOX_SPACE - i * mm_1
                                - BOX_RECT_BOLD_LINE / 2, width - BOX_SPACE - BOX_RECT_WIDTH / 2,
                        height - BOX_SPACE - i * mm_1 - BOX_RECT_BOLD_LINE
                                / 2, gridPaint);
            } else
            {
                gridPaint.setStrokeWidth(BOX_RECT_NORMAL_LINE);
                canvas.drawLine(BOX_SPACE + BOX_RECT_WIDTH / 2, height - BOX_SPACE - i * mm_1
                        - BOX_RECT_NORMAL_LINE / 2, width - BOX_SPACE - BOX_SPACE - BOX_RECT_WIDTH / 2, height
                        - BOX_SPACE - i * mm_1 - BOX_RECT_NORMAL_LINE
                        / 2, gridPaint);
            }

        }
        /* draw h line */
        for (int i = 1; i < maxLineW; i++)
        {
            if (i % 5 == 0)
            {
                gridPaint.setStrokeWidth(BOX_RECT_BOLD_LINE);
                canvas.drawLine(BOX_SPACE + i * mm_1
                        - BOX_RECT_BOLD_LINE / 2, BOX_SPACE + BOX_RECT_WIDTH / 2, BOX_SPACE
                        + i * mm_1 - BOX_RECT_BOLD_LINE / 2, height
                        - BOX_SPACE - BOX_RECT_WIDTH / 2, gridPaint);
            } else
            {
                gridPaint.setStrokeWidth(BOX_RECT_NORMAL_LINE);
                canvas.drawLine(
                        BOX_SPACE + i * mm_1 - BOX_RECT_NORMAL_LINE / 2,
                        BOX_SPACE + BOX_RECT_WIDTH / 2,
                        BOX_SPACE + i * mm_1 - BOX_RECT_NORMAL_LINE / 2,
                        height - BOX_SPACE - BOX_RECT_WIDTH / 2, gridPaint);
            }
        }

    }
}

