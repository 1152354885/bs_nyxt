package com.me.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUsernameAndPassword(String username, String password);

    int insertUser(User user);

    List<User> selectByUsername(String username);

    int updateByPrimaryKey(User user);

    User selectByPrimaryKey(Integer id);

    void update(User user);

    List<User> searchByKeyWord(String KeyWord);
}
