package com.esales.microservicemodel.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private Integer userId;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String street;
    private int postalCode;
    private String city;
    private boolean isVoluntary;
    private boolean isResponsible;
    private String token;
}
