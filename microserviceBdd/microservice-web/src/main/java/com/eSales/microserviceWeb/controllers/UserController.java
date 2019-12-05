package com.eSales.microserviceWeb.controllers;

import com.eSales.microserviceBusiness.SecurityToken.JwtUserDetailsService;
import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.entity.User;
import com.eSales.microserviceModel.entities.dto.UserDto;
import com.eSales.microserviceModel.entities.mapper.contract.UserMapper;
import com.eSales.microserviceWeb.security.JwtTokenUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserManager userManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    static final Log logger = LogFactory.getLog(UserController.class);

    /**
     * get all users
     * @return
     */
    @GetMapping(value = "/AllUsers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * check user login and password
     * @param userDto
     * @return httpResponse 404 or 200 and new token if needed when login and password id ok.
     */
    @PostMapping(value = "/checkUserLogIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> checkUserLogin(@RequestBody UserDto userDto) {
        User userInput;
        boolean userInputIsValidate;
        User fullUserFromBdd;
        UserDto fullUserFromBddDto;

        userInput = userMapper.fromDtoToUserWithoutAddress(userDto);
        userInputIsValidate = userManager.checkIfUserMailAndPasswordIsOk(userInput);

        if (userInputIsValidate) {
            fullUserFromBdd = userDao.findByEmail(userInput.getEmail());
            fullUserFromBddDto = userMapper.fromUserToDto(fullUserFromBdd);
            // token creation
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userInput.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
//            return ResponseEntity.ok(new JwtResponse(token));
            fullUserFromBddDto.setToken(token);
            fullUserFromBddDto.setPassword(null);
            return ResponseEntity.ok(fullUserFromBddDto);
        } else {
            return (new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    /**
     * for create new user
     * @param newUserDto
     * @return
     */
    @PostMapping(value = "/newUser", consumes = "application/json")
    public HttpStatus newUser(@RequestBody UserDto newUserDto) {

        boolean addNewUserIsOk = userManager.addUser(newUserDto);

        if (addNewUserIsOk) {
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.CONFLICT;
        }
    }
}
