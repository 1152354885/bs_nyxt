package com.me.test.controller;

import com.me.test.pojo.Image;
import com.me.test.sevice.ImageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("")指定路径名

@RequestMapping(value = "/api/image")
public class ImageController {
    @Autowired
    private ImageSevice imageSevice;
    @GetMapping(value = "queryAll")
    @ResponseBody
    public List<Image> queryAll() {
        List<Image> ImageList = imageSevice.queryAll();
        System.out.println(ImageList);
        return ImageList;
    }
    @PostMapping(value = "add")
    @ResponseBody
    public String add( Image image){
      System.out.println(image);
      imageSevice.add(image);
        return "添加成功";
    }
    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public  String delete(@PathVariable("id") Integer id)
    {
     imageSevice.delete(id);

        return "删除成功";
    }



}
