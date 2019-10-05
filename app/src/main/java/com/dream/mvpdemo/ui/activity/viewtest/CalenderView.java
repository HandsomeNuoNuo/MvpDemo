package com.dream.mvpdemo.ui.activity.viewtest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dream.mvpdemo.R;
import com.necer.calendar.MonthCalendar;
import com.necer.calendar.WeekCalendar;
import com.necer.painter.InnerPainter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**********************************
 * @Name: CalenderView
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/10/1 17:53
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class CalenderView extends LinearLayout {
    private View view;
    private MonthCalendar monthCalendar;
    private WeekCalendar weekCalendar;
    private LinearLayout lin_mon,lin_week,lin;
    private boolean isWeekShow = true;
    Animation hideanimation,showanimation;
    public CalenderView(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.view_calendar,this);
        init();
    }

    public CalenderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.view_calendar,this);
        init();
    }

    public CalenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.view_calendar,this);
        init();
    }

    private void init(){
        monthCalendar = view.findViewById(R.id.monthCalendar);
        weekCalendar = view.findViewById(R.id.weekCalendar);
        lin_mon = findViewById(R.id.lin_mon);
        lin_week = findViewById(R.id.lin_week);

        showanimation = AnimationUtils.loadAnimation(getContext(), R.anim.showalpha);
        hideanimation = AnimationUtils.loadAnimation(getContext(), R.anim.hidealpha);

        monthCalendar.setBackgroundColor(getResources().getColor(R.color.no_color));

        weekCalendar.setBackgroundColor(getResources().getColor(R.color.no_color));


        List<String> pointList = Arrays.asList("201910-01", "2019-10-3", "2019-10-8", "2019-10-15", "2019-10-23", "2019-10-28");

        InnerPainter painter = (InnerPainter)monthCalendar.getCalendarPainter();
        painter.setPointList(pointList);


        Map<String, String> strMap = new HashMap<>();
        strMap.put("2019-10-25", "测试");
        strMap.put("2019-01-23", "测试1");
        strMap.put("2019-01-24", "测试2");
        painter.setReplaceLunarStrMap(strMap);

        Map<String, Integer> colorMap = new HashMap<>();
        colorMap.put("2019-10-25", Color.RED);

        colorMap.put("2019-10-5", Color.parseColor("#000000"));
        painter.setReplaceLunarColorMap(colorMap);


        List<String> holidayList = new ArrayList<>();
        holidayList.add("2019-10-20");
        holidayList.add("2019-10-21");
        holidayList.add("2019-10-22");

        List<String> workdayList = new ArrayList<>();
        workdayList.add("2019-10-21");
        workdayList.add("2019-10-22");
        workdayList.add("2019-10-23");

        painter.setLegalHolidayList(holidayList, workdayList);
    }

    public void changeWeekOrMonth(){
        if (isWeekShow) {
            isWeekShow = false;
            lin_week.startAnimation(hideanimation);
            lin_week.setVisibility(View.GONE);

            lin_mon.startAnimation(showanimation);
            lin_mon.setVisibility(View.VISIBLE);
        } else {
            isWeekShow = true;

            lin_mon.startAnimation(hideanimation);
            lin_mon.setVisibility(View.GONE);

            lin_week.startAnimation(showanimation);
            lin_week.setVisibility(View.VISIBLE);
        }
    }

}
