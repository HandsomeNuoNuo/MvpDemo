package com.dream.mvpdemo.model.bean;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 * @文件名称 : House
 * @文件作者 : 黄峰
 * @创建时间 : 2019/8/23 15:15
 * @文件描述 : 
 * @版权声明 : Copyright (C) 2008-2016 北斗天汇（北京）科技有限公司
 * @修改历史 : 2019/8/23
 ******************************************************************/

public class House
{
    private final String TAG="House";
    public  String door;
    public  String window = "";
    public  String step;
    public  int price;

    public House(String door, List<String> windows, String step, int price)
    {
        this.door = door;
        for (String w:windows){
            this.window += w+" ";
        }
        this.step = step;
        this.price = price;
    }

    public static class Builder{
        final List<String> windows = new ArrayList<>();
        String door;
        String step;
        int price;

        public Builder()
        {
        }

        public Builder setDoor(String door)
        {
            this.door = door;
            return this;
        }

        public Builder addWindow(String window)
        {
            windows.add(window);
            return this;
        }

        public Builder setStep(String step)
        {
            this.step = step;
            return this;
        }

        public Builder setPrice(int price)
        {
            this.price = price;
            return this;
        }

        public House build(){
            return new House(door,windows,step,price);
        }
    }
}
