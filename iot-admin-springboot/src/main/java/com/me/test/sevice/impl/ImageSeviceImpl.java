package com.me.test.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.test.mapper.ImageMapper;
import com.me.test.pojo.Image;
import com.me.test.sevice.ImageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageSeviceImpl implements ImageSevice {
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public List<Image> queryAll() {
        QueryWrapper wrapper = new QueryWrapper();

       return imageMapper.selectList((Wrapper<Image>) wrapper.orderByDesc("upload_time"));
    }

    @Override
    public void add(Image image) {
        imageMapper.add(image);
    }

    @Override
    public void delete(Integer id) {
         imageMapper.delete(id);
    }
}
