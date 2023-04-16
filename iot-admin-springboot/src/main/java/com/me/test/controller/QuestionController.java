package com.me.test.controller;


import com.me.test.pojo.Question;
import com.me.test.sevice.QuestionService;

import com.me.test.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("list")
    public Object list(){
        List<Question> questions = questionService.queryAll();
        return ResponseUtil.okList(questions);
    }

}
