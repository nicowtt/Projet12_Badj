package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.dto.ArticleToyDto;
import com.eSales.microserviceModel.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleManager {
    Integer findNextArticleNumberOnOneSale(int saleId);
    boolean addNewBookArticle(ArticleBookDto articleBookDto, int userId);
    boolean addNewObjectArticle(ArticleObjectDto articleObjectDto, int userId);
    boolean addNewClotheArticle(ArticleClotheDto articleClotheDto, int userId);
    boolean addNewToyArticle(ArticleToyDto articleToyDto, int userId);
    List<Article> getAllArticlesForOneUser(int userId);
    void removeArticle(Article article);
}
