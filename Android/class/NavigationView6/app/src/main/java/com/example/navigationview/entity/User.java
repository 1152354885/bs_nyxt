package com.example.navigationview.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer sex;
    private String dateOfBirth;
    private Integer pwdProtectionId;
    private String pwdProtectionVal;

    public User() {
    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        username = in.readString();
        password = in.readString();
        nickName = in.readString();
        if (in.readByte() == 0) {
            sex = null;
        } else {
            sex = in.readInt();
        }
        dateOfBirth = in.readString();
        if (in.readByte() == 0) {
            pwdProtectionId = null;
        } else {
            pwdProtectionId = in.readInt();
        }
        pwdProtectionVal = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(nickName);
        if (sex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(sex);
        }
        parcel.writeString(dateOfBirth);
        if (pwdProtectionId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(pwdProtectionId);
        }
        parcel.writeString(pwdProtectionVal);
    }
}