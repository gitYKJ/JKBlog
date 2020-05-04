package com.jkblog.entity.massgefactory;

public interface MessageFactory<T> {
    /*
    * 一、兴趣爱好的消息
    * 二、关注订阅发布通知
    *
    * */
    //接口中的属性默认是 final static
    //接口中的方法默认是public abstract
    public Message  product(Class<Message> msg);
}
