package com.jkblog.service.impl;

import com.jkblog.dao.ArticleDao;
import com.jkblog.entity.Article;
import com.jkblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void addWen(Article article) {
        articleDao.addWen(article);
    }
}
