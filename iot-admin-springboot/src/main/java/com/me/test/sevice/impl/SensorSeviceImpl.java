package com.me.test.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.test.mapper.ImageMapper;
import com.me.test.mapper.SensorMapper;
import com.me.test.pojo.Datainfo_datainfo;
import com.me.test.pojo.Image;
import com.me.test.sevice.SensorSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SensorSeviceImpl implements SensorSevice {

    @Autowired
    private SensorMapper sensorMapper;
    @Override
    public List<Datainfo_datainfo> queryAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.last("limit 50");
        return sensorMapper.selectList((Wrapper<Datainfo_datainfo>) wrapper.orderByDesc("time"));
    }

    @Override
    public List<Datainfo_datainfo> search(String date1,String date2) {
        return sensorMapper.searchTime(date1,date2);
    }
}
