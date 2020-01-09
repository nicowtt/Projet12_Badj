package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.contract.SaleManager;
import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class SaleController {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SaleManager saleManager;

    /**
     * get all sales
     * @return
     */
    @GetMapping(value = "/AllSales")
    public List<Sale> getAllSales() {
        return saleDao.findAll();
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

    /**
     * get all next sales (next than begin-date!) (user not connected)
     * @return -> list of sale number
     */
    @GetMapping(value = "/AfterTodaySales")
    public List<Sale> getAfterTodaySales() {
        return saleDao.getSalesByDateBeginAfterToday();
    }

    /**
     * get all next sales (next than begin-date!) (user is connected)
     * @param userEmail -> input from front
     * @return -> list of sale with number of article pre-record on each sale
     */
    @GetMapping(value = "/AfterTodaySales/{userEmail}")
    public List<SaleDto> getAfterTodaySales(@PathVariable String userEmail) {
        List<SaleDto> afterTodaySalesDtoList;
        List<Sale> afterTodaySalesList = saleManager.getSalesByDateBeginAfterToday();

        User user = userDao.findByEmail(userEmail);
        // pre-record added on sale list
        afterTodaySalesDtoList = saleManager.getSalesByDateBeginAfterTodayWithNbrOfPreArticleRecord(afterTodaySalesList, user);
        return afterTodaySalesDtoList;
    }
}
