package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.DateManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class DateManagerImpl implements DateManager {

    static final Log logger = LogFactory.getLog(DateManagerImpl.class);

}
