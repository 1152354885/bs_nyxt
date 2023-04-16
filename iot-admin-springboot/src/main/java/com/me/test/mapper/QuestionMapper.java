package com.me.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.test.pojo.Image;
import com.me.test.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
