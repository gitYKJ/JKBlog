package com.jkblog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MessageController {
    int a=1;
    @RequestMapping("/")
    @ResponseBody
    public String testData(){
        return "线程数为"+a++;
    }

}
