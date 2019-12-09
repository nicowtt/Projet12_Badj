package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserManager {

    boolean checkIfUserMailAndPasswordIsOk(User userToValidate);
    boolean checkIfMailExist(String mail);
    boolean addUser(UserDto userDto) ;
}
