package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entities.entity.Address;
import com.eSales.microserviceModel.entities.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {
}
