package com.me.test.sevice;



import com.me.test.pojo.Datainfo_datainfo;

import java.util.List;


public interface SensorSevice {


    List<Datainfo_datainfo> queryAll();
    List<Datainfo_datainfo> search(String date1,String date2);

}

