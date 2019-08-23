package com.dream.mvpdemo.model.bean;
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
    public  String window;
    public  String step;
    public  int price;

    public House(String door, String window, String step, int price)
    {
        this.door = door;
        this.window = window;
        this.step = step;
        this.price = price;
    }

    public static class Builder{
        String door;
        String window;
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

        public Builder setWindow(String window)
        {
            this.window = window;
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
            return new House(door,window,step,price);
        }
    }
}
