package com.dream.mvpdemo.ui.activity.litepal;

import com.dream.mvpdemo.model.bean.People;
import com.dream.mvpdemo.model.db.DbHelper;

import java.util.List;

/**LitePalModel
 * Created by Administrator on 2018/5/7.
 */

public class LitePalModel implements LitePalContract.LitePalModel{

    public LitePalModel() {

    }

    @Override
    public People savePeople(String name,int age, String sex){
        People people = new People();
        people.setName(name);
        people.setAge(age);
        people.setSex(sex);
        if(DbHelper.getInstance().savePeople(people))
        return people;

        return null;
    }

    @Override
    public List<People> getAllPeople()
    {
        return DbHelper.getInstance().getAllPeople();
    }

    public People getByID(int id){

        return DbHelper.getInstance().getByID(id);
    }
}
