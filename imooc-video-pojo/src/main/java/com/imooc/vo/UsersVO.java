package com.imooc.vo;

import com.imooc.pojo.Users;

public class UsersVO extends Users {

    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

}
