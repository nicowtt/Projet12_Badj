package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);


    @Override
    public boolean checkIfUserMailAndPasswordIsOk(User userToValidate) {
        User userOnBdd = new User();
        boolean mailExist = false;
        boolean mailAndUserExist = false;

        // check if email exist on bdd
        mailExist = this.checkIfMailExist(userToValidate.getEmail());

        if (mailExist) {
            // get user on bdd
            userOnBdd = userDao.findByEmail(userToValidate.getEmail());
            //compare password
            if (userOnBdd.getPassword().equals(userToValidate.getPassword())) {
                mailAndUserExist = true;
            }
        }
        return  mailAndUserExist;
    }

    @Override
    public boolean checkIfMailExist(String email) {
        boolean mailExist = false;

        User oneUser = userDao.findByEmail(email);
        if (oneUser != null) {
            mailExist = true;
        } else {
            logger.info("L'utilisateur: " + email + " n'existe pas en BDD.");
        }
        return mailExist;
    }

}
