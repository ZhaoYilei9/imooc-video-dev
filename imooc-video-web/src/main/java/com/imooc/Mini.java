package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.imooc.mapper")
@SpringBootApplication
public class Mini {
    public static void main(String[] args) {
        SpringApplication.run(Mini.class,args);
    }
}
