package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserManager userManager;

    /**
     * get all users
     * @return
     */
    @GetMapping(value = "/AllUsers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    @PostMapping(value = "/checkUserLogIn", consumes = "application/json")
    public boolean checkUser(@RequestBody User user) {
        boolean userIsValid = false;
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userIsValid = userManager.checkIfUserMailAndPasswordIsOk(user);
        System.out.println(userIsValid);
        return userIsValid;
    }
}
