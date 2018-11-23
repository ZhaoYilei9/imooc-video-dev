package com.imooc.controller;

import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user_redis_session";

    public static final String FILE_SPACE = "/home/zyl/imooc-video-dev/";


}
