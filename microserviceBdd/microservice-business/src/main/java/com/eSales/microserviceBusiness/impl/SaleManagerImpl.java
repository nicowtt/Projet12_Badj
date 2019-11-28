package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.SaleManager;
import com.eSales.microserviceDao.SaleDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SaleManagerImpl implements SaleManager {

    @Autowired
    private SaleDao saleDao;

    static final Log logger = LogFactory.getLog(SaleManagerImpl.class);

}
