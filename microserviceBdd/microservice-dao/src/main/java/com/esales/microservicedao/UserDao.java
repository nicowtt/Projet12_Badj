package com.esales.microservicedao;

import com.esales.microservicemodel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String userEmail);

    @Query(value = "SELECT (email) FROM users", nativeQuery = true)
    List<String> getAllUsersEmails();

}
