package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao extends JpaRepository<Sale, Integer> {

    @Query(value = "SELECT * FROM sales WHERE date_begin > current_date ", nativeQuery = true)
    List<Sale> getSalesByDateBeginAfterToday();

    Sale findById(int id);

    @Query(value = "SELECT * FROM sales WHERE date_begin = ?1", nativeQuery = true)
    Sale getSale(String date);

}
