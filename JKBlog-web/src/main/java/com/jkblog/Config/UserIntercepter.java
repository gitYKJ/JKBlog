package com.jkblog.Config;

import com.jkblog.util.ObjectMapperUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class UserIntercepter implements HandlerInterceptor {
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器开始工作!!!!!");
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            // log.info("OPTIONS请求，放行");
            System.out.println("通过");
            return true;
        }
        String token = null;
        //获取Cookie信息
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies) {
            if("userLogin".equals(cookie.getName())){
                token=cookie.getValue();
                break;
            }
        }
        //校验token是否有效
        if(!StringUtils.isEmpty(token)){
           String userJSON= jedisCluster.get(token);
           if(!StringUtils.isEmpty(userJSON)){
               User user=ObjectMapperUtil.toObject(userJSON, User.class);
           }
        }
        response.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
