package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entity.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClotheDao extends JpaRepository<Clothe, Integer> {
}
