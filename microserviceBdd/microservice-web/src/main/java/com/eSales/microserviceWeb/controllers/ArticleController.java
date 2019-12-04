package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceModel.entities.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    /**
     * Get all articles
     * @return
     */
    @GetMapping(value = "/AllArticles")
    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }
}
