package com.esales.microservicebusiness.contract;

import com.esales.microservicemodel.entity.Address;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AddressManager {

    void removeAddress(Address address);
    Optional<Address> getAddressById(Integer id);
}
