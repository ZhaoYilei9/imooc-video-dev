package com.imooc.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.imooc.pojo.Users;

import java.util.List;

public interface UserService {
    void saveUser(Users user);
    boolean queryForUserIsExit(String username);
    PageInfo selectAllUsers(int page, int rows);
}
