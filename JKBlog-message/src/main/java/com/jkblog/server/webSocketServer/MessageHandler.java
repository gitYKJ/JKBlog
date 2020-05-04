package com.jkblog.server.webSocketServer;

import com.jkblog.entity.massgefactory.GzMessage;
import com.jkblog.entity.massgefactory.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
*1、监控队列中是否为空
*2、获取队列中的消息推送到用户
*1、在添加文章后查询到用户封装到list中，遍历list获取到关联的chanel,并判断chanel是否在线，
* 在线则推送，数据库新增消息变为已发送，相反新增未发送
*二、可以用netty的group来监控队列，节约资源
* 内部类不能访问外部类的方法？
* */
public class MessageHandler{

    private Task task=new Task();
    private Message message;
    public Message newMsg(){
       ExecutorService service= Executors.newSingleThreadExecutor();
       service.execute(task);
       return message;
    }
    class Task implements Runnable{
        @Override
        public void run() {
            message=new GzMessage();
        }
        public void sysOut(){
            System.out.println(message);
        }
    }
    public String sendMessage(){
        //mh.channelRead0();
        return "success";
    }


}
