package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.dto.ArticleBookDto;
import org.springframework.stereotype.Service;

@Service
public interface ArticleManager {
    int findNextArticleNumberOnOneSale(int saleId);
    boolean addNewBookArticle(ArticleBookDto articleBookDto, int userId);
}
