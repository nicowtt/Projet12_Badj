package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.ArticleManager;
import com.eSales.microserviceDao.*;
import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.dto.ArticleToyDto;
import com.eSales.microserviceModel.entity.*;
import com.eSales.microserviceModel.entity.Object;
import com.eSales.microserviceModel.mapper.contract.ArticleBookMapper;
import com.eSales.microserviceModel.mapper.contract.ArticleClotheMapper;
import com.eSales.microserviceModel.mapper.contract.ArticleObjectMapper;
import com.eSales.microserviceModel.mapper.contract.ArticleToyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        newArticle = articleDao.save(articleInputFromDao);

        // new book -> bdd
        newBookFromDto = articleBookMapper.fromArticleBookDtoToBook(articleBookDto);
        newBookFromDto.setArticle(newArticle);
        bookDao.save(newBookFromDto);

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
        newArticle = articleDao.save(articleInputFromDao);

        // new Object -> bdd
        newObjectFromDto = articleObjectMapper.fromArticleObjectDtoToObject(articleObjectDto);
        newObjectFromDto.setArticle(newArticle);
        objectDao.save(newObjectFromDto);

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
        newArticle = articleDao.save(articleInputFromDao);

        // new object -> bdd
        newClotheFromDto = articleClotheMapper.fromArticleClotheDtoToClothe(articleClotheDto);
        newClotheFromDto.setArticle(newArticle);
        clotheDao.save(newClotheFromDto);

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
        newArticle = articleDao.save(articleInputfromDao);

        // new object -> bdd
        newToyfromDto = articleToyMapper.fromArticleToyDtoToToy(articleToyDto);
        newToyfromDto.setArticle(newArticle);
        toyDao.save(newToyfromDto);

        return true;
    }
}
