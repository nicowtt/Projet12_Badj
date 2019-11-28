package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.contract.SaleManager;
import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SaleController {

    @Autowired
    private SaleDao saleDao;

    /**
     * get all sales
     * @return
     */
    @GetMapping(value = "/AllSales")
    public List<Sale> getAllSales() {
        return saleDao.findAll();
    }

    /**
     * get all next sales (next than begin-date!)
     * @return
     */
    @GetMapping(value = "/AfterTodaySales")
    public List<Sale> getAfterTodaySales() {
        return saleDao.getSalesByDateBeginAfterToday();
    }
}
