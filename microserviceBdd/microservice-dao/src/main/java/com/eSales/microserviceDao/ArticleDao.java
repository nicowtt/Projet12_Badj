package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    @Query(value = "SELECT MAX(sale_number) FROM articles WHERE sale_id = ?1", nativeQuery = true)
    int getLargestArticleNumber(int saleId);
}
