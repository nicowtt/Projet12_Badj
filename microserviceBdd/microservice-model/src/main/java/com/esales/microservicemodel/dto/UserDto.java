package com.esales.microservicemodel.dto;


import com.esales.microservicemodel.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private Integer id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private boolean isVoluntary;
    private boolean isResponsible;
    private String token;
    private Address address;
}
