package com.me.test.sevice;



import com.me.test.pojo.Datainfo_datainfo;
import com.me.test.pojo.Datainfo_rfidinfo;

import java.util.List;


public interface WarnSevice {


    List<Datainfo_rfidinfo> queryAll();
    List<Datainfo_rfidinfo> search(String date1,String date2);

}
