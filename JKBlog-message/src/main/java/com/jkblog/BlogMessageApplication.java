package com.jkblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.push.message.dao")
public class BlogMessageApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogMessageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world！");
    }
}
