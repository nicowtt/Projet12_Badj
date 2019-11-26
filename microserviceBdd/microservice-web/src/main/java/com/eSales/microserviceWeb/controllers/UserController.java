package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * get all users
     * @return
     */
    @GetMapping(value = "/AllUsers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
