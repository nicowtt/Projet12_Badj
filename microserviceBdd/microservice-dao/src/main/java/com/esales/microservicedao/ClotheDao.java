package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClotheDao extends JpaRepository<Clothe, Integer> {
}
