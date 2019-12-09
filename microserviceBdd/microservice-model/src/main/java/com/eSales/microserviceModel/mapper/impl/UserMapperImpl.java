package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.contract.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {


    /**
     * For map UserDto to User without address
     * @param userDto -> token + user + adress
     * @return user
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
     * @param user user
     * @return userDto
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

    /**
     * for map UserDto to address
     * @param userDto (token + user + adress)
     * @return address bean
     */
    @Override
    public Address fromUserDtoToAddress(UserDto userDto) {
        Address address = new Address();

        address.setStreet(userDto.getStreet());
        address.setPostalCode(userDto.getPostalCode());
        address.setCity(userDto.getCity());

        return address;
    }
}
