package com.me.test.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.test.mapper.AdminMapper;
import com.me.test.pojo.Admin;
import com.me.test.sevice.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>  implements AdminService{
    @Autowired
    private  AdminMapper adminMapper;
    @Override
    public boolean login(String username, String password) {
        //   UserEntity userEntity = new UserEntity ();
        Admin userInfo=new Admin();
//        userEntity.setUsername ( username );
//        userEntity.setPassword ( password );
        userInfo.setUsername( username );
        userInfo.setPassword( password );


//        Admin user = userInfoMapper.selectUser( userInfo);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        Admin user = adminMapper.selectOne(wrapper);
        if (user != null){
            return true;
        }
        return false;

    }

}



