package com.imooc.controller;

import com.imooc.enums.ErrorMsgEnums;
import com.imooc.exception.RegistException;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;
import com.imooc.vo.UsersVO;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegistryAndLoginController extends BaseController{

    @Autowired
    private UserService userService;

    @PostMapping("regist")
    public IMoocJSONResult userRegistry(@RequestBody Users users) throws Exception {

        if (users == null){
            return IMoocJSONResult.errorMsg("");
        }
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return IMoocJSONResult.errorMsg(ErrorMsgEnums.USER_NOT_NULL.msg);
        }

        boolean flag = userService.queryForUserIsExit(users.getUsername());
        if (flag){
            return IMoocJSONResult.errorMsg(ErrorMsgEnums.USER_EXIST.msg);
        }

        users.setNickname(users.getUsername());
        users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
        users.setFansCounts(0);
        users.setFollowCounts(0);
        users.setReceiveLikeCounts(0);
        userService.regist(users);

        UsersVO usersVO = this.setUserSessionToken(users);
        usersVO.setPassword("");
        return IMoocJSONResult.ok(usersVO);
    }

    private UsersVO setUserSessionToken(Users users) {
        String sessionKey = USER_REDIS_SESSION + users.getId();
        String userToken = UUID.randomUUID().toString();
        redis.set(sessionKey,userToken);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users,usersVO);
        usersVO.setUserToken(userToken);
        return usersVO;
    }

    @PostMapping("login")
    public IMoocJSONResult userLogin(@RequestBody Users users) throws Exception{

        if (users == null){
            return IMoocJSONResult.errorMsg("");
        }
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return IMoocJSONResult.errorMsg(ErrorMsgEnums.USER_NOT_NULL.msg);
        }
        users = userService.queryForLogin(users);
        if(users == null){
            return IMoocJSONResult.errorMsg(ErrorMsgEnums.USERNAME_NOT_SUIT_PASSWORD.msg);
        }
        UsersVO usersVO = this.setUserSessionToken(users);


        return IMoocJSONResult.ok(usersVO);
    }


}
