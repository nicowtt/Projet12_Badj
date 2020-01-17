package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {

    UserDto fromUserToDto(User user);
    User fromDtoToUserWithoutAddress(UserDto userDto);
    Address fromUserDtoToAddress(UserDto userDto);
}
