package com.me.test.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.test.pojo.Admin;

public interface AdminService extends IService<Admin> {

    boolean login(String username, String password);
}
