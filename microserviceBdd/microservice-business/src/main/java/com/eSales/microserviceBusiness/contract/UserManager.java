package com.eSales.microserviceBusiness.contract;

import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserManager {

    boolean checkIfUserMailAndPasswordIsOk(User userToValidate);
    boolean checkIfMailExist(String mail);
    boolean addUser(UserDto userDto) ;
    List<User> getAllUsers();
    User findUserByMail(String email);
}
