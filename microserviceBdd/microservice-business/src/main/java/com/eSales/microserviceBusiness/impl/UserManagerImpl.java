package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);


    @Override
    public boolean checkIfUserMailAndPasswordIsOk(User userToValidate) {
        User userOnBdd = new User();
        boolean mailExist = false;
        boolean passwordIsValid = false;
        boolean mailAndUserExist = false;

        // check if email exist on bdd
        mailExist = this.checkIfMailExist(userToValidate.getEmail());

        if (mailExist) {
            // get user on bdd
            userOnBdd = userDao.findByEmail(userToValidate.getEmail());
            //compare password
            passwordIsValid = passwordEncoder.checkPassword(userToValidate.getPassword(), userOnBdd.getPassword());
            if (passwordIsValid) {
                mailAndUserExist = true;
                logger.info("L'utilisateur " + userToValidate.getEmail() + " est validé.");
            } else {
                logger.info("L'utilisateur " + userToValidate.getEmail() + " n'a pas rentré le bon mot de passe.");
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
