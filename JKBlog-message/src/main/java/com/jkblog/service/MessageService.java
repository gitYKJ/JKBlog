package com.jkblog.service;

import com.jkblog.entity.massgefactory.MessageFactory;

public interface MessageService {
    //用户接收消息
    ///public MessageFactory recive(String username, int i);
    //消息增删改查
    /*
    * 用户推送消息
    * 1、当用户关注的相关的文章发布时推送消息
    * 2、当用户发布消息后推送消息到粉丝
    * 根据标签和用户粉丝查询到用户数量去重后推送
    * */
    public void push(String username, MessageFactory msg);
}
