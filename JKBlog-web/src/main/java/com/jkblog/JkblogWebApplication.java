package com.jkblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(/*exclude = {SecurityAutoConfiguration.class}*/)
@MapperScan("com.jkblog.dao")
public class JkblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(JkblogWebApplication.class, args);
    }

}
