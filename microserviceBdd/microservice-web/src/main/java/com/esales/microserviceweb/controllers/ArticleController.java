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
import java.util.Optional;

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
     * for remove article
     * @param articleDto -> to remove
     * @return ok if removed
     */
    @PostMapping(value = "/RemoveArticle")
    public ResponseEntity<String> removeArticle(@RequestBody ArticleDto articleDto) {
        boolean articleRemoved = false;
        Article articleToRemove = articleMapper.fromArticleDtoToArticle(articleDto);
        try {
            articleManager.removeArticle(articleToRemove);
            articleRemoved = true;
        } catch (UnexpectedRollbackException e) {
            logger.warn( "roll back on remove article");
        }
        if (articleRemoved) {
            return (new ResponseEntity<>(HttpStatus.OK));
        } else {
            return (new ResponseEntity<>("error on removed article",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Get all articles for one sale
     * @return all articles
     */
    @GetMapping(value = "/AllArticlesForSale/{saleId}")
    public List<Article> getAllArticlesForOneSale(@PathVariable int saleId) {
        return articleManager.getAllArticlesForOneSale(saleId);
    }

    /**
     * For update any sort of articles
     * @param articleDto -> input
     * @return 200 if article is updated
     */
    @PostMapping(value = "/UpdateArticle", consumes = "application/json")
    public ResponseEntity<String> updateArticle(@RequestBody ArticleDto articleDto) {
        boolean articleIsUpdated = articleManager.updateArticle(articleDto);
        if (articleIsUpdated) {
            return (new ResponseEntity<>(HttpStatus.OK));
        } else {
            return (new ResponseEntity<>("error on article update",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * for get one article with his id
     * @param articleId -> from front
     * @return article
     */
    @GetMapping(value = "/getOneArticle/{articleId}")
    public Optional<Article> getOneArticleWithSaleNumberAndSaleId(@PathVariable int articleId) {
        return Optional.ofNullable(articleDao.getArticlesById(articleId));
    }

    /**
     * for get article with saleNumber and saleId
     * @param saleNumber -> from front
     * @param saleId -> from front
     * @return article
     */
    @GetMapping(value = "/getOneArticleWithSaleNumberAndSaleId/{saleNumber}/{saleId}")
    public Optional<Article> getOneArticleWithArticleId(@PathVariable int saleNumber, @PathVariable int saleId) {
        return articleManager.getOneArticleWithSaleNumberAndSaleId(saleNumber, saleId);
    }


}
