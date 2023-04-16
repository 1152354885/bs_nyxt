package com.me.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.test.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
