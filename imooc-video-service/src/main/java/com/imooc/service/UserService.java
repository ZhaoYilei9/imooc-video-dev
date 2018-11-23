package com.imooc.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.imooc.pojo.Users;
import com.imooc.vo.UsersVO;

import java.util.List;

public interface UserService {
    boolean queryForUserIsExit(String username);
    PageInfo selectAllUsers(int page, int rows);

    void regist(Users users);

    Users queryForLogin(Users users) throws Exception;

    Users queryUserByPrimaryKey(String userId);

    void updateUser(Users users);
}
