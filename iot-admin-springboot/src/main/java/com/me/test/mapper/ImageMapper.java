package com.me.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.test.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImageMapper  extends BaseMapper<Image> {
      void add(Image image);
      void delete(Integer id);
}
