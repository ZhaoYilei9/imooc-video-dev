package com.imooc.service.conf;

import org.n3r.idworker.Sid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Sid getSid(){
        Sid sid = new Sid();
        return sid;
    }
}
