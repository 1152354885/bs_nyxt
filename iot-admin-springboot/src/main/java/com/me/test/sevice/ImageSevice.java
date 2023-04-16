package com.me.test.sevice;

import com.me.test.pojo.Image;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ImageSevice {


    List<Image> queryAll();
    void add(Image image);
    void delete(Integer id);
}

