package com.eSales.microserviceModel.entities.mapper.impl;

import com.eSales.microserviceModel.entities.dto.UserDto;
import com.eSales.microserviceModel.entities.entity.User;
import com.eSales.microserviceModel.entities.mapper.contract.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {


    /**
     * For map UserDto to User without address
     * @param userDto
     * @return
     */
    @Override
    public User fromDtoToUserWithoutAddress(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        return user;
    }

    /**
     * For map User to UserDto
     * @param user
     * @return
     */
    @Override
    public UserDto fromUserToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());

        userDto.setStreet(user.getAddress().getStreet());
        userDto.setPostalCode(user.getAddress().getPostalCode());
        userDto.setCity(user.getAddress().getCity());

        return userDto;
    }
}
