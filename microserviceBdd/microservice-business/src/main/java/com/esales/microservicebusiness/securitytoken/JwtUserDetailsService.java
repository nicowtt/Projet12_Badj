package com.esales.microservicebusiness.securitytoken;


import java.util.ArrayList;

import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.entity.User;
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
