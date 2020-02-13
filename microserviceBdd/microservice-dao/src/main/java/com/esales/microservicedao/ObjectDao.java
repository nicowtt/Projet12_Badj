package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectDao extends JpaRepository<Object, Integer> {
}
