package com.me.test.sevice;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.test.mapper.QuestionMapper;
import com.me.test.pojo.Datainfo_rfidinfo;
import com.me.test.pojo.Question;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;


    public List<Question> queryAll() {
        QueryWrapper wrapper = new QueryWrapper();

        return questionMapper.selectList(null);
    }
}
