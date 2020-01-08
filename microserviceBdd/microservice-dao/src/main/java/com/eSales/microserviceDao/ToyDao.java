package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entity.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyDao extends JpaRepository<Toy, Integer> {
}
