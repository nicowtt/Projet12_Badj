package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.dto.ArticleToyDto;
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
     * @return all articles
     */
    @GetMapping(value = "/AllArticles")
    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }

    /**
     * Persist new clothe Article
     * @param newArticleClotheDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewClotheArticle", consumes = "application/json")
    public ResponseEntity<String> addClotheArticle(@RequestBody ArticleClotheDto newArticleClotheDto) {
        // todo crée une methode pour ajouter l'article en bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }

    /**
     * Persist new object article
     * @param newArticleObjectDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewObjectArticle", consumes = "application/json")
    public ResponseEntity<String> addObjectArticle(@RequestBody ArticleObjectDto newArticleObjectDto) {
        // todo crée une méthode pour ajouter l'article dans la bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }

    /**
     * Persist new Toy article
     * @param newArticleToyDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewToyArticle", consumes = "application/json")
    public ResponseEntity<String> addToyArticle(@RequestBody ArticleToyDto newArticleToyDto) {
        // todo crée une méthode pour ajouter l'article dans la bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }

    /**
     * Persist new Book article
     * @param newArticleBookDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/newBookArticle", consumes = "application/json")
    public ResponseEntity<String> addBookArticle(@RequestBody ArticleBookDto newArticleBookDto) {
        // todo crée une méthode pour ajouter l'article dans la bdd
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }
}
