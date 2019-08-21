package com.dream.mvpdemo.model;

import com.dream.mvpdemo.contract.LitePalContract;
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
    public boolean savePeople(String name,int age, String sex){
        People people = new People();
        people.setName(name);
        people.setAge(age);
        people.setSex(sex);
        return DbHelper.getInstance().savePeople(people);
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
