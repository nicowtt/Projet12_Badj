package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    @Query(value = "SELECT MAX(sale_number) FROM articles WHERE sale_id = ?1", nativeQuery = true)
    Integer getLargestArticleNumber(int saleId);

    @Query(value = "SELECT COUNT(id) FROM articles WHERE user_id = ?1 AND sale_id = ?2", nativeQuery = true)
    Integer getNbrOfArticlesForOneUserAndOneSale(int userId, int saleId);

    @Query(value = "SELECT * FROM articles WHERE user_id = ?1", nativeQuery = true)
    List<Article> getAllArticlesByUserId(int userId);

    @Query(value = "SELECT * FROM articles WHERE sale_id = ?1", nativeQuery = true)
    List<Article> getAllArticlesBySaleId(int saleId);

    Article getArticlesById(int id);

    @Query(value = "SELECT * FROM articles WHERE sale_number = ?1 AND sale_id = ?2", nativeQuery = true)
    Article getArticleBySaleNumberAndSaleId(int saleNumber, int saleId);
}
