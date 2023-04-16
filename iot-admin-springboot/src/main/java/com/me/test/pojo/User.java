package com.me.test.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class User {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @TableField(value = "nickName")
    private String nickName;
    private Integer sex;
    @TableField(value = "dateOfBirth")
    private String dateOfBirth;
    @TableField(value = "pwdProtectionId")
    private Integer pwdProtectionId;
    @TableField(value = "pwdProtectionVal")
    private String pwdProtectionVal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPwdProtectionId() {
        return pwdProtectionId;
    }

    public void setPwdProtectionId(Integer pwdProtectionId) {
        this.pwdProtectionId = pwdProtectionId;
    }

    public String getPwdProtectionVal() {
        return pwdProtectionVal;
    }

    public void setPwdProtectionVal(String pwdProtectionVal) {
        this.pwdProtectionVal = pwdProtectionVal;
    }

}


