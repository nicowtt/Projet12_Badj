package com.eSales.microserviceBusiness.SecurityToken;


import java.util.ArrayList;

import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * load UserDetails (security bean) from user on bdd
     * @param userEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User userOnBdd = userDao.findByEmail(userEmail);
        return new org.springframework.security.core.userdetails.User(userOnBdd.getEmail(), userOnBdd.getPassword(), new ArrayList<>());
    }
}
