package com.me.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.test.pojo.Datainfo_datainfo;
import com.me.test.pojo.Image;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
@Mapper

public interface SensorMapper extends BaseMapper<Datainfo_datainfo> {

  public List<Datainfo_datainfo> searchTime(String date1, String date2);

}
