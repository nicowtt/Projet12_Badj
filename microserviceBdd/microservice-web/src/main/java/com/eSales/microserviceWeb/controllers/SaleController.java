package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
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
