package com.jkblog.service;

import com.jkblog.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public void addUser(User user);

    public String userSelect(User user, HttpServletResponse response, HttpServletRequest request);
}
