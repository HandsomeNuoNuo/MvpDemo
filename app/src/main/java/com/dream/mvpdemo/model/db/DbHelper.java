package com.dream.mvpdemo.model.db;

import android.util.Log;

import com.dream.mvpdemo.model.bean.People;

import org.litepal.LitePal;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */

public class DbHelper implements AppDbHelper{

    private static DbHelper instance;

    public static DbHelper getInstance()
    {
        if(instance == null) instance = new DbHelper();
        return instance;
    }

    @Override
    public void testDb() {
        Log.d("print", "数据库操作");
    }

    public boolean savePeople(People people){

        return people.save();
    }

    public People getByID(int id){
        List<People> list = LitePal.where("id = "+id+"?",id+"").find(People.class);
        return list.size() > 0?list.get(0):null;
    }

    public List<People> getAllPeople(){
        return LitePal.findAll(People.class);
    }
}
