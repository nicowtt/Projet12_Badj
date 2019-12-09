package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceDao.AddressDao;
import com.eSales.microserviceModel.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    /**
     * get all addresses
     * @return
     */
    @GetMapping(value = "/AllAddresses")
    public List<Address> getAllAddresses() {
        return addressDao.findAll();
    }
}
