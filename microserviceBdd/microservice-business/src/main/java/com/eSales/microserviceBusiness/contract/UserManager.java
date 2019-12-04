package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.entities.entity.User;
import com.eSales.microserviceModel.entities.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserManager {

    boolean checkIfUserMailAndPasswordIsOk(User userToValidate);
    boolean checkIfMailExist(String mail);
    void addUser(UserDto userDto);
}
