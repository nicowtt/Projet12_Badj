package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController {

    @Autowired
    private SaleDao saleDao;

    /**
     * get all sales
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/AllSales")
    public List<Sale> getAllSales() {
        return saleDao.findAll();
    }
}
