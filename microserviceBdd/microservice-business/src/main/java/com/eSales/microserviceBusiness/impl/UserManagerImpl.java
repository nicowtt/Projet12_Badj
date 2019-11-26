package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.UserDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);

}
