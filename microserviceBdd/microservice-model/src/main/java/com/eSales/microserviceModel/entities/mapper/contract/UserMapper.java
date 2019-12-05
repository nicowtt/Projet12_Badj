package com.eSales.microserviceModel.entities.mapper.contract;

import com.eSales.microserviceModel.entities.dto.UserDto;
import com.eSales.microserviceModel.entities.entity.Address;
import com.eSales.microserviceModel.entities.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {

    UserDto fromUserToDto(User user);
    User fromDtoToUserWithoutAddress(UserDto userDto);
    Address fromUserDtoToAddress(UserDto userDto);
}
