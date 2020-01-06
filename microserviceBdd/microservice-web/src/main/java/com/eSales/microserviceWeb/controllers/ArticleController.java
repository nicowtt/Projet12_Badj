package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Add new clothe Article
     * @param newArticleClotheDto
     * @return
     */
    @PostMapping(value = "/NewClotheArticle", consumes = "application/json")
    public ResponseEntity<String> addClotheArticle(@RequestBody ArticleClotheDto newArticleClotheDto) {
        System.out.println(newArticleClotheDto);
        // todo crée une methode pour ajouter l'article en bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }

    @PostMapping(value = "/NewObjectArticle", consumes = "application/json")
    public ResponseEntity<String> addObjectArticle(@RequestBody ArticleObjectDto newArticleObjectDto) {
        System.out.println(newArticleObjectDto);
        // todo crée une méthode pour ajouter l'article dans la bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }
}
