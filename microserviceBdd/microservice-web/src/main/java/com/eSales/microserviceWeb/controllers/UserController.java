package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.SecurityToken.JwtUserDetailsService;
import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.dto.security.JwtResponse;
import com.eSales.microserviceModel.entities.User;
import com.eSales.microserviceWeb.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserManager userManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * get all users
     * @return
     */
    @GetMapping(value = "/AllUsers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * check user login and password
     * @param user
     * @return httpResponse 404 or 200 and new token if needed when login and password id ok.
     */
    @PostMapping(value = "/checkUserLogIn", consumes = "application/json")
    public ResponseEntity<?> checkUserLogin(@RequestBody User user) {
        boolean userIsValid = false;

        userIsValid = userManager.checkIfUserMailAndPasswordIsOk(user);
        if (userIsValid) {
            // token is create
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(user.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return (new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
}
