package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.AddressManager;
import com.eSales.microserviceDao.AddressDao;
import com.eSales.microserviceModel.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressManagerImpl implements AddressManager {

    @Autowired
    private AddressDao addressDao;

    /**
     * for deleting an address
     * @param address
     */
    @Override
    public void removeAddress(Address address) {
        addressDao.delete(address);
    }

    /**
     * get address with a ID
     * @param id
     * @return
     */
    @Override
    public Optional<Address> getAddressById(Integer id) {
        return addressDao.findById(id);
    }
}
