package com.esales.microservicebusiness.impl;

import com.esales.microservicebusiness.contract.ArticleManager;
import com.esales.microservicedao.*;
import com.esales.microservicemodel.dto.ArticleBookDto;
import com.esales.microservicemodel.dto.ArticleClotheDto;
import com.esales.microservicemodel.dto.ArticleObjectDto;
import com.esales.microservicemodel.dto.ArticleToyDto;
import com.esales.microservicemodel.entity.*;
import com.esales.microservicemodel.entity.Object;
import com.esales.microservicemodel.mapper.contract.ArticleBookMapper;
import com.esales.microservicemodel.mapper.contract.ArticleClotheMapper;
import com.esales.microservicemodel.mapper.contract.ArticleObjectMapper;
import com.esales.microservicemodel.mapper.contract.ArticleToyMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ArticleManagerImpl implements ArticleManager {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleBookMapper articleBookMapper;

    @Autowired
    private ArticleObjectMapper articleObjectMapper;

    @Autowired
    private ArticleClotheMapper articleClotheMapper;

    @Autowired
    private ArticleToyMapper articleToyMapper;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ObjectDao objectDao;

    @Autowired
    private ClotheDao clotheDao;

    @Autowired
    private ToyDao toyDao;

    static final Log logger = LogFactory.getLog(ArticleManagerImpl.class);

    /**
     * To get the next article sale number for one sale
     * @param saleId -> sale concerned
     * @return next article sale number for sale concerned
     */
    @Override
    public Integer findNextArticleNumberOnOneSale(int saleId) {
        Integer largestArticleNumber = articleDao.getLargestArticleNumber(saleId);
        // if there is no article for this sale
        if (largestArticleNumber == null) {
            largestArticleNumber = 0;
        }
        return largestArticleNumber + 1;
    }

    /**
     * to persist new book article
     * @param articleBookDto -> from front
     * @param userId -> from front (who want to create object)
     * @return -> true if object is persist
     */
    @Override
    @Transactional
    public boolean addNewBookArticle(ArticleBookDto articleBookDto, int userId) {
        int nextArticleNumber = this.findNextArticleNumberOnOneSale(articleBookDto.getSaleId());
        Article articleInputFromDao;
        Article newArticle;
        Book newBookFromDto;

        // new article -> bdd
        articleInputFromDao = articleBookMapper.fromArticleBookDtoToArticle(articleBookDto,userId, nextArticleNumber);
        try {
            newArticle = articleDao.save(articleInputFromDao);

            // new book -> bdd
            newBookFromDto = articleBookMapper.fromArticleBookDtoToBook(articleBookDto);
            newBookFromDto.setArticle(newArticle);
            bookDao.save(newBookFromDto);
        } catch (DataIntegrityViolationException e) {
            logger.warn("SaleId and SaleNumber must be unique.");
        }
        return true;
    }

    /**
     * to persist new Object article
     * @param articleObjectDto -> input from front
     * @param userId -> user from front (who want to create object)
     * @return -> true if object is persist
     */
    @Override
    @Transactional
    public boolean addNewObjectArticle(ArticleObjectDto articleObjectDto, int userId) {
        int nextArticleNumber = this.findNextArticleNumberOnOneSale(articleObjectDto.getSaleId());
        Article articleInputFromDao;
        Article newArticle;
        Object newObjectFromDto;

        // new article -> bdd
        articleInputFromDao = articleObjectMapper.fromArticleObjectDtoToArticle(articleObjectDto, userId, nextArticleNumber);
        try {
            newArticle = articleDao.save(articleInputFromDao);

            // new Object -> bdd
            newObjectFromDto = articleObjectMapper.fromArticleObjectDtoToObject(articleObjectDto);
            newObjectFromDto.setArticle(newArticle);
            objectDao.save(newObjectFromDto);
        } catch (DataIntegrityViolationException e) {
            logger.warn("SaleId and SaleNumber must be unique.");
        }
        return true;
    }

    /**
     * to persist new clothe article
     * @param articleClotheDto -> input from front
     * @param userId -> user from front (who want to create object)
     * @return true if clothe article is persist on bdd
     */
    @Override
    @Transactional
    public boolean addNewClotheArticle(ArticleClotheDto articleClotheDto, int userId) {
        int nextArticleNumber = this.findNextArticleNumberOnOneSale(articleClotheDto.getSaleId());
        Article articleInputFromDao;
        Article newArticle;
        Clothe newClotheFromDto;

        // new article -> bdd
        articleInputFromDao = articleClotheMapper.fromArticleClotheDtoToArticle(articleClotheDto, userId, nextArticleNumber);
        try {
            newArticle = articleDao.save(articleInputFromDao);

            // new object -> bdd
            newClotheFromDto = articleClotheMapper.fromArticleClotheDtoToClothe(articleClotheDto);
            newClotheFromDto.setArticle(newArticle);
            clotheDao.save(newClotheFromDto);
        } catch (DataIntegrityViolationException e) {
            logger.warn("SaleId and SaleNumber must be unique.");
        }
        return true;
    }

    /**
     * to persist new toy article
     * @param articleToyDto -> input from front
     * @param userId -> user from front (who want to create object)
     * @return true if toy article is persist on bdd
     */
    @Override
    @Transactional
    public boolean addNewToyArticle(ArticleToyDto articleToyDto, int userId) {
        int nextArticleNumber = this.findNextArticleNumberOnOneSale(articleToyDto.getSaleId());
        Article articleInputfromDao;
        Article newArticle;
        Toy newToyfromDto;

        // new article -> bdd
        articleInputfromDao = articleToyMapper.fromArticleToyDtoToArticle(articleToyDto, userId, nextArticleNumber);
        try {
            newArticle = articleDao.save(articleInputfromDao);

            // new object -> bdd
            newToyfromDto = articleToyMapper.fromArticleToyDtoToToy(articleToyDto);
            newToyfromDto.setArticle(newArticle);
            toyDao.save(newToyfromDto);
        } catch (DataIntegrityViolationException e) {
            logger.warn("SaleId and SaleNumber must be unique.");
        }
        return true;
    }

    /**
     * get All Articles For One User
     * @param userId -> user concerned
     * @return -> list of Articles
     */
    @Override
    public List<Article> getAllArticlesForOneUser(int userId) {
        return articleDao.getAllArticlesByUserId(userId);
    }

    /**
     * for delete article
     * @param article to delete
     */
    @Override
    public void removeArticle(Article article) {
        if (article.getBook() != null) {
            bookDao.delete(article.getBook());
        }
        if (article.getClothe() != null) {
            clotheDao.delete(article.getClothe());
        }
        if (article.getObject() != null) {
            objectDao.delete(article.getObject());
        }
        if (article.getToy() != null) {
            toyDao.delete(article.getToy());
        }
        articleDao.delete(article);
    }

    /**
     * get All Articles For One Sale
     * @param saleId for articles
     * @return list of articles
     */
    @Override
    public List<Article> getAllArticlesForOneSale(int saleId) {
        return articleDao.getAllArticlesBySaleId(saleId);
    }
}
