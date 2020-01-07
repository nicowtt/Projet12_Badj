package com.eSales.microserviceDao;

import com.eSales.microserviceModel.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
