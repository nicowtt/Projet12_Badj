package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectDao extends JpaRepository<Object, Integer> {
}
