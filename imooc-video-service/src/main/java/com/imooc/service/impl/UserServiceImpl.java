package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;

import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryForUserIsExit(String username) {
        Users user = new Users();
        user.setUsername(username);
        Users result = usersMapper.selectOne(user);
        return result == null ? false : true;
    }


    @Override
    public PageInfo selectAllUsers(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Users> users = usersMapper.selectAll();


        PageInfo pageInfo = new PageInfo(users);
        pageInfo.setTotal(users.size());

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void regist(Users users) {
        String id = sid.nextShort();
        users.setId(id);

        usersMapper.insertSelective(users);
    }

    @Override
    public Users queryForLogin(Users users) throws Exception{
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",users.getUsername());
        criteria.andEqualTo("password", MD5Utils.getMD5Str(users.getPassword()));
        List<Users> list = usersMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            return list.get(0);
        }

        return null;
    }

    @Override
    public Users queryUserByPrimaryKey(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        return users;
    }

    @Override
    public void updateUser(Users users) {
        usersMapper.updateByPrimaryKey(users);
    }
}
