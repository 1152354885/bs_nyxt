package com.me.test.controller;


import com.me.test.config.ResponseCode;
import com.me.test.pojo.Admin;
import com.me.test.pojo.Image;
import com.me.test.pojo.User;
import com.me.test.sevice.AdminService;
import com.me.test.sevice.UserService;
import com.me.test.utils.JacksonUtil;
import com.me.test.utils.ResponseUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public Object login(@RequestBody String body) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }
        User user = userService.login(username, password);
        if (user == null) {
            return ResponseUtil.fail(ResponseCode.AUTH_INVALID_ACCOUNT, "用户名密码错误");
        }
        System.out.println("成功");
        System.out.println(ResponseUtil.ok(user));
        return ResponseUtil.ok(user);
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) {
        if (user == null) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getNickName()) || StringUtils.isEmpty(user.getDateOfBirth()) || StringUtils.isEmpty(user.getPwdProtectionVal())) {
            return ResponseUtil.badArgument();
        }
        String username = user.getUsername();
        List<User> users = userService.queryByUserName(username);
        if (users.size() > 0){
            return ResponseUtil.fail(ResponseCode.AUTH_NAME_REGISTERED,"用户名已注册");
        }
        if (userService.register(user) == 0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    @PutMapping("forgetPwd")
    public Object forgetPwd(@RequestBody String body){
        String username = JacksonUtil.parseString(body,"username");
        String password = JacksonUtil.parseString(body,"password");
        Integer pwdProtectionId = JacksonUtil.parseInteger(body,"pwdProtectionId");
        String pwdProtectionVal = JacksonUtil.parseString(body,"pwdProtectionVal");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(pwdProtectionVal) || pwdProtectionId == null){
            return ResponseUtil.badArgument();
        }
        List<User> users = userService.queryByUserName(username);
        if (users.size() == 0){
            return ResponseUtil.fail(ResponseCode.AUTH_INVALID_ACCOUNT,"没有找到该用户");
        }
        if (users.size() > 1){
            return ResponseUtil.serious();
        }
        User user = users.get(0);
        Integer _pwdProtectionId = user.getPwdProtectionId();
        String _pwdProtectionVal = user.getPwdProtectionVal();
        if (_pwdProtectionId == null || _pwdProtectionVal == null){
            return ResponseUtil.serious();
        }
        if (!_pwdProtectionId.equals(pwdProtectionId) || !_pwdProtectionVal.equals(pwdProtectionVal)){
            return ResponseUtil.fail(ResponseCode.AUTH_PWD_PROTECTION_UNMATCH,"密保错误");
        }
        user.setPassWord(password);
        if (userService.updateById(user) == 0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }
    @DeleteMapping(value = "delete/{id}")
    public  Object delete(@PathVariable("id") Integer id)
    {
        userService.deleteById(id);

        return ResponseUtil.ok();
    }
    @GetMapping(value = "queryBydP")
    @ResponseBody
    public Object queryAllBydp(@RequestParam("dp") String dp) {
        List<User> UserList = userService.queryByDepartment(dp);
       // System.out.println(ImageList);
        return ResponseUtil.ok(UserList);
    }
    @GetMapping(value = "queryAll")
    @ResponseBody
    public Object queryAll() {
        List<User> UserList = userService.queryAll();

        return ResponseUtil.okList(UserList);

    }
    @PutMapping("updateById")
    public Object updateById(@RequestBody String body){
        String username = JacksonUtil.parseString(body,"username");
        String password = JacksonUtil.parseString(body,"password");
        String dp = JacksonUtil.parseString(body,"nickName");
        Integer id = JacksonUtil.parseInteger(body,"id");
       // Integer pwdProtectionId = JacksonUtil.parseInteger(body,"pwdProtectionId");
        String dateOfBirth = JacksonUtil.parseString(body,"dateOfBirth");
        String pwdProtectionVal = JacksonUtil.parseString(body,"pwdProtectionVal");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(dp) || id  == null){
            return ResponseUtil.badArgument();
        }
        User user = userService.queryById(id);

       user.setPassWord(password);
       user.setNickName(dp);
       user.setUserName(username);
       user.setDateOfBirth(dateOfBirth);
       System.out.println(user);
       userService.update(user);
        return ResponseUtil.ok();
    }
    @PostMapping("/admin/login")
    @ResponseBody
    public  Object login (@RequestBody Admin admin){
        System.out.println(admin);
        System.out.println ( "管理员调用接口！！！用户名:" + admin.getUsername() + "密码:" + admin.getPassword() );
        boolean login = adminService.login ( admin.getUsername(), admin.getPassword() );
        if (login) {
            return ResponseUtil.ok();
        }
        return ResponseUtil.badArgument();
    }
   //模糊查询
   @PostMapping ("/search")
   @ResponseBody
   public Object search(@RequestBody String keyword) {
       String k = JacksonUtil.parseString(keyword,"keyword");
       System.out.println("关键词" + k);
       List<User> UserList = userService.searchByKeyWord(k);
       return ResponseUtil.okList(UserList);
   }
}


