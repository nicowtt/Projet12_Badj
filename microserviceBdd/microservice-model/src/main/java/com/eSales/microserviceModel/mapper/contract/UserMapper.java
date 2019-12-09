package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {

    UserDto fromUserToDto(User user);
    User fromDtoToUserWithoutAddress(UserDto userDto);
    Address fromUserDtoToAddress(UserDto userDto);
}
