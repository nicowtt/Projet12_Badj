package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.ArticleManager;
import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceDao.BookDao;
import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Book;
import com.eSales.microserviceModel.mapper.contract.ArticleBookMapper;
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
    private BookDao bookDao;

    /**
     * To get the next article sale number for one sale
     * @param saleId -> sale concerned
     * @return next article sale number for sale concerned
     */
    @Override
    public int findNextArticleNumberOnOneSale(int saleId) {
        int largestArticleNumber = articleDao.getLargestArticleNumber(saleId);
        return largestArticleNumber + 1;
    }

    /**
     * to persist new book article
     * @param articleBookDto -> from front
     * @param userId -> from controller
     * @return -> boolean
     */
    @Override
    @Transactional
    public boolean addNewBookArticle(ArticleBookDto articleBookDto, int userId) {
        int nextArticleNumber = this.findNextArticleNumberOnOneSale(articleBookDto.getSaleNumber());
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
}
