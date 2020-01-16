package com.esales.microserviceweb.controllers;

import com.esales.microservicebusiness.contract.ArticleManager;
import com.esales.microservicedao.ArticleDao;
import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.ArticleMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleManager articleManager;

    @Autowired
    private ArticleMapper articleMapper;

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
        boolean addNewClotheIsOk = false;
        User userWhoCreate = userDao.findByEmail(newArticleClotheDto.getUserEmail());
        try {
            addNewClotheIsOk = articleManager.addNewClotheArticle(newArticleClotheDto, userWhoCreate.getId());
        } catch (UnexpectedRollbackException e) {
            logger.warn( "roll back on new clothe article");
        }
        if (addNewClotheIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("error on clothe article record",HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

    /**
     * Persist new object article
     * @param newArticleObjectDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewObjectArticle", consumes = "application/json")
    public ResponseEntity<String> addObjectArticle(@RequestBody ArticleObjectDto newArticleObjectDto) {
        boolean addNewObjectIsOk = false;
        User userWhoCreate = userDao.findByEmail(newArticleObjectDto.getUserEmail());
        try {
            addNewObjectIsOk = articleManager.addNewObjectArticle(newArticleObjectDto, userWhoCreate.getId());
        } catch (UnexpectedRollbackException e) {
            logger.warn( "roll back on new object article");
        }
        if (addNewObjectIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("error on object article record",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Persist new Toy article
     * @param newArticleToyDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewToyArticle", consumes = "application/json")
    public ResponseEntity<String> addToyArticle(@RequestBody ArticleToyDto newArticleToyDto) {
        boolean addNewToyArticleIsOk = false;
        User userWhoCreate = userDao.findByEmail(newArticleToyDto.getUserEmail());
        try {
            addNewToyArticleIsOk = articleManager.addNewToyArticle(newArticleToyDto, userWhoCreate.getId());
        } catch (UnexpectedRollbackException e) {
            logger.warn( "roll back on new toy article");
        }
        if (addNewToyArticleIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("error on toy article record",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Persist new Book article
     * @param newArticleBookDto from front
     * @return httpResponse
     */
    @PostMapping(value = "/NewBookArticle", consumes = "application/json")
    public ResponseEntity<String> addBookArticle(@RequestBody ArticleBookDto newArticleBookDto) {
        boolean addNewBookArticleIsOk = false;
        User userWhoCreate = userDao.findByEmail(newArticleBookDto.getUserEmail());
        try {
            addNewBookArticleIsOk = articleManager.addNewBookArticle(newArticleBookDto, userWhoCreate.getId());
        } catch (UnexpectedRollbackException e) {
            logger.warn("roll back on new book article");
        }
        if (addNewBookArticleIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("error on book article record",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * get all articles for one user
     * @param userEmail -> input from front
     * @return -> list of articles
     */
    @GetMapping(value = "/AllArticlesForId/{userEmail}")
    public List<Article> getAllArticleForOneId(@PathVariable String userEmail) {
        User userConcerned = userDao.findByEmail(userEmail);
        List<Article> articlesList = articleManager.getAllArticlesForOneUser(userConcerned.getId());
        return articlesList;
    }


    /**
     * For remove article and get a new user article list
     * @param articleDto -> to delete
     * @param userEmail -> user who want the new article list
     * @return -> article list updated
     */
    @PostMapping(value = "/RemoveArticleAndGetNewList/{userEmail}")
    public List<Article> removeArticleAndGetNewList(@RequestBody ArticleDto articleDto, @PathVariable String userEmail) {
        Article articleToRemove = articleMapper.fromArticleDtoToArticle(articleDto);
        articleManager.removeArticle(articleToRemove);
        User userConcerned = userDao.findByEmail(userEmail);
        List<Article> articlesList = articleManager.getAllArticlesForOneUser(userConcerned.getId());
        return articlesList;
    }

}
