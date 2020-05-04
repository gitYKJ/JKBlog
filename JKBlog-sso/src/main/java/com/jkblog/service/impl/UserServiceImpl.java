package com.jkblog.service.impl;

import com.jkblog.dao.UserMapper;
import com.jkblog.pojo.User;
import com.jkblog.service.UserService;
import com.jkblog.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    @Override
    public void addUser(User user) {
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        userMapper.insert(user);
    }

    @Override
    public String userSelect(User user, HttpServletResponse response, HttpServletRequest request) {
        String token = null;
        //1.将密码进行加密
        String md5Pass =
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        User user1=userMapper.select(user);
        if(user1!=null){
            //String token="user_"+ UUID.randomUUID().toString();
            token="jkblog"+user1.getUsername()+System.currentTimeMillis();
            token=DigestUtils.md5DigestAsHex(token.getBytes());
            //添加token到redis中
            String userJSON= ObjectMapperUtil.toJSON(user1);
            //jedisCluster.setex(token,7*24*3600, userJSON);
        }
        return token;
    }
}