package com.esales.microservicebusiness.contract;

import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.dto.UserDto;
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
