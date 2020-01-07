package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.contract.ArticleManager;
import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.dto.ArticleToyDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleManager articleManager;

    static final Log logger = LogFactory.getLog(ArticleController.class);

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
        Date test = newArticleClotheDto.getRecordDate();
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
        boolean addNewBookArticleIsOk = false;
        User user = userDao.findByEmail(newArticleBookDto.getUserEmail());
        try {
            addNewBookArticleIsOk = articleManager.addNewBookArticle(newArticleBookDto, user.getId());
        } catch (UnexpectedRollbackException e) {
            logger.warn("roll back on new book article");
        }
        if (addNewBookArticleIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("error on article record",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
