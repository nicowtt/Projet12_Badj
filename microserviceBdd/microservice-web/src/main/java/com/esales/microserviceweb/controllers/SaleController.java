package com.esales.microserviceweb.controllers;

import com.esales.microservicebusiness.contract.SaleManager;
import com.esales.microservicedao.SaleDao;
import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

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
        return saleDao.getSalesByDateEndAfterToday();
    }

    /**
     * get all next sales (next than begin-date!) (user is connected)
     * @param userEmail -> input from front
     * @return -> list of sale with number of article pre-record on each sale
     */
    @ApiOperation(value = "get all sales (after today) with all pre-record articles for one user")
    @GetMapping(value = "/AfterTodaySales/{userEmail}")
    public List<SaleDto> getAfterTodaySales(@PathVariable String userEmail) {
        List<SaleDto> afterTodaySalesDtoList;
        List<Sale> afterTodaySalesList = saleManager.getSalesByDateEndAfterToday();

        User user = userDao.findByEmail(userEmail);
        // pre-record added on sale list
        afterTodaySalesDtoList = saleManager.getSalesByDateBeginAfterTodayWithNbrOfPreArticleRecord(afterTodaySalesList, user);
        return afterTodaySalesDtoList;
    }

    /**
     * for add new Sale
     * @param saleDto -> from front
     * @return saleRecorded
     */
    @PostMapping(value = "/NewSale")
    public Sale addNewSale(@RequestBody SaleDto saleDto) {
        return saleManager.addSale(saleDto);
    }

    /**
     * For remove sale if begin date is before than today
     * @param saleDto -> from front
     * @return
     */
    @PostMapping(value = "/RemoveSale")
    public ResponseEntity<String> deleteSale(@RequestBody SaleDto saleDto) {
        boolean saleIsDeleted = saleManager.deleteSaleIfBeginDateIsAfterToday(saleDto);
        if (saleIsDeleted) {
            return (new ResponseEntity<>(HttpStatus.OK));
        } else {
            return (new ResponseEntity<>("sale not removed, today date is after sale date begin", HttpStatus.ACCEPTED));
        }
    }

}
