package com.esales.microservicedao;

import com.esales.microservicemodel.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
