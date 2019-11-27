package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDao extends JpaRepository<Sale, Integer> {
}
