package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserManager {

    boolean checkIfUserMailAndPasswordIsOk(User userToValidate);
    boolean checkIfMailExist(String mail);
}
