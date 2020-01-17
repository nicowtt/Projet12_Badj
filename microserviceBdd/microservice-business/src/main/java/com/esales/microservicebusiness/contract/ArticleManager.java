package com.esales.microservicebusiness.contract;

import com.esales.microservicemodel.dto.ArticleBookDto;
import com.esales.microservicemodel.dto.ArticleClotheDto;
import com.esales.microservicemodel.dto.ArticleObjectDto;
import com.esales.microservicemodel.dto.ArticleToyDto;
import com.esales.microservicemodel.entity.Article;
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
    List<Article> getAllArticlesForOneSale(int saleId);
}
