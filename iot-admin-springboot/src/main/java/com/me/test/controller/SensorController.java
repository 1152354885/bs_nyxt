package com.me.test.controller;
import com.me.test.pojo.Datainfo_datainfo;
import com.me.test.pojo.Datainfo_rfidinfo;

import com.me.test.sevice.SensorSevice;
import com.me.test.sevice.WarnSevice;
import com.me.test.utils.JacksonUtil;
import com.me.test.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
@Controller
@RequestMapping(value = "/api/iot")
public class SensorController {
    @Autowired
    private SensorSevice sensorSevice;
    @Autowired
    private WarnSevice warnSevice;
    @GetMapping(value = "sensor")
    @ResponseBody
    public Object queryAll() {
        List<Datainfo_datainfo> datainfo_datainfosList = sensorSevice.queryAll();
        System.out.println(datainfo_datainfosList);
        return ResponseUtil.okList(datainfo_datainfosList);
    }
    @GetMapping(value = "warn")
    @ResponseBody
    public Object queryAllWarn() {
        List<Datainfo_rfidinfo> datainfo_rfidinfoList = warnSevice.queryAll();
        System.out.println(datainfo_rfidinfoList);
        return ResponseUtil.okList(datainfo_rfidinfoList);
    }
    @PostMapping(value = "search")
    @ResponseBody
    public Object queryAllByDate(@RequestBody String body) {
        String date1 = JacksonUtil.parseString(body,"date1");
        String date2= JacksonUtil.parseString(body,"date2");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(date1);   System.out.println(date2);
//        dateFormat.format(date1);
//        dateFormat.format(date2);
        List<Datainfo_datainfo> datainfo_datainfosList = sensorSevice.search(date1,date2);
        System.out.println(datainfo_datainfosList);
        return ResponseUtil.okList(datainfo_datainfosList);
    }
    @PostMapping(value = "searchwarn")
    @ResponseBody
    public Object queryWarnAllByDate(@RequestBody String body) {
        String date1 = JacksonUtil.parseString(body,"date1");
        String date2= JacksonUtil.parseString(body,"date2");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(date1);   System.out.println(date2);
//        dateFormat.format(date1);
//        dateFormat.format(date2);
        List<Datainfo_rfidinfo> datainfo_rfidinfoList = warnSevice.search(date1,date2);
        System.out.println(datainfo_rfidinfoList);
        return ResponseUtil.okList(datainfo_rfidinfoList);
    }
}

