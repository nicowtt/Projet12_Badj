package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.entity.Address;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AddressManager {

    void removeAddress(Address address);
    Optional<Address> getAddressById(Integer id);
}
