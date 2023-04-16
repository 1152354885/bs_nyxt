package com.me.test.sevice;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.test.mapper.UserMapper;
import com.me.test.pojo.Question;
import com.me.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(String username, String password){
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public int register(User user){
       return userMapper.insertUser(user);
    }

    public List<User> queryByUserName(String username){
        return userMapper.selectByUsername(username);
    }

    public int updateById(User user){
        return userMapper.updateByPrimaryKey(user);
    }

    public User queryById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int deleteById(Integer id){return  userMapper.deleteById(id);}

    public List<User> queryByDepartment(String nickName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("nickName",nickName);
        return   userMapper.selectList(wrapper);
    }
    public List<User> queryAll() {
        QueryWrapper wrapper = new QueryWrapper();

        return userMapper.selectList(null);
    }
    public List<User> searchByKeyWord (String KeyWord){

        return userMapper.searchByKeyWord(KeyWord);
    }
}

