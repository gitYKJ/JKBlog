package com.jkblog.controller;

import com.jkblog.entity.Article;
import com.jkblog.result.Result;
import com.jkblog.server.webSocketServer.MessageHandler;
import com.jkblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    MessageHandler mh=new MessageHandler();
    @RequestMapping("/list")
    @ResponseBody
    public String lieBiao(){
        return "文章列表内容";
    }
    @RequestMapping("/add")
    @ResponseBody
    @Transactional
    public Result addWen(@RequestBody Article article ,HttpServletResponse response){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        article.setCreate_time(dateString);
        article.setId(3);
//        Article article=new Article("2020-01-01",1,"aaa",2,"java");
        articleService.addWen(article);
        //1、如果有关注标签或关注该用户，否则不发送
        //发送消息 消息处理器
        //2、AOP在方法执行完后发送消
        mh.sendMessage();
        return new Result(200,"success",null);
    }
}