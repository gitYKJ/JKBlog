package com.jkblog;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@ComponentScan(basePackages = {"com.jkblog.service.impl"})
@MapperScan("com.jkblog.dao")
public class JkBlogManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(JkBlogManageApplication.class, args);
    }

}
