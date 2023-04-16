package com.me.test.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.test.mapper.SensorMapper;
import com.me.test.mapper.WarnMapper;
import com.me.test.pojo.Datainfo_datainfo;
import com.me.test.pojo.Datainfo_rfidinfo;
import com.me.test.sevice.WarnSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarnSeviceImpl implements WarnSevice {
    @Autowired
    private WarnMapper warnMapper;
    @Override
    public List<Datainfo_rfidinfo> queryAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.last("limit 50");
        return warnMapper.selectList((Wrapper<Datainfo_rfidinfo>) wrapper.orderByDesc("time"));
    }

    @Override
    public List<Datainfo_rfidinfo> search(String date1, String date2) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.ge("time",date1);
        wrapper.lt("time",date2);
        wrapper.orderByDesc("time");
        return warnMapper.selectList(wrapper) ;
    }

}
