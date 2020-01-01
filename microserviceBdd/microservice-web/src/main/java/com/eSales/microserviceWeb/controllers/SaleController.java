package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

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

    /**
     * get one sale
     * @param saleId -> sale ID
     * @return
     */
    @GetMapping(value = "/OneSale/{saleId}")
    public Optional<Sale> getOneSale(@PathVariable Integer saleId) {
        return saleDao.findById(saleId);
    }
}
