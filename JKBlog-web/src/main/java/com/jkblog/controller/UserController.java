package com.jkblog.controller;

import com.jkblog.pojo.User;
import com.jkblog.result.Result;
import com.jkblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody  User user, HttpServletRequest request, HttpServletResponse response){
        //返回类型补登 ctrl+alt+v
        System.out.println(user);
        String token=userService.userSelect(user,response, request);
        if(token!=null){
            Cookie cookie=new Cookie("userLogin",token);
            //cookie.setDomain("http://localhost:8082");
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return new Result(200,"success",null);
    }
    @PostMapping("/addUser")
    @ResponseBody
    public void addUser( User user){
        // @RequestBody问题
        userService.addUser(user);
    }
    /*@PostMapping("/login")
    @ResponseBody
    public String loginTest(@RequestBody User user, HttpServletRequest request){

        System.out.println(request.getParameterNames().hasMoreElements());
        System.out.println(user.getPassword());
        return "success";
    }*/
}
