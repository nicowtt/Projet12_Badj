package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyDao extends JpaRepository<Toy, Integer> {
}
