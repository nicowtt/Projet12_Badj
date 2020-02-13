package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.UserMapper;
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

        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setVoluntary(userDto.isVoluntary());
        user.setResponsible(userDto.isResponsible());

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
        Address address = new Address();

        address.setId(user.getId());
        address.setStreet(user.getAddress().getStreet());
        address.setPostalCode(user.getAddress().getPostalCode());
        address.setCity(user.getAddress().getCity());

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setVoluntary(user.isVoluntary());
        userDto.setResponsible(user.isResponsible());

        userDto.setAddress(address);

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

        address.setStreet(userDto.getAddress().getStreet());
        address.setPostalCode(userDto.getAddress().getPostalCode());
        address.setCity(userDto.getAddress().getCity());

        return address;
    }
}
