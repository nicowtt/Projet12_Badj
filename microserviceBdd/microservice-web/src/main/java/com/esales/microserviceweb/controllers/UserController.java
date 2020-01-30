package com.esales.microserviceweb.controllers;

import com.esales.microservicebusiness.securitytoken.JwtUserDetailsService;
import com.esales.microservicebusiness.contract.UserManager;
import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.mapper.contract.UserMapper;
import com.esales.microserviceweb.security.JwtTokenUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.UnexpectedRollbackException;
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
            fullUserFromBddDto.setToken(token);
            fullUserFromBddDto.setPassword(null);
            return ResponseEntity.ok(fullUserFromBddDto);
        } else {
            return (new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
        }
    }

    /**
     * for create new user
     * @param newUserDto
     * @return
     */
    @PostMapping(value = "/newUser", consumes = "application/json")
    public ResponseEntity<String> newUser(@RequestBody UserDto newUserDto) {
        boolean addNewUserIsOk = false;
        try {
            addNewUserIsOk = userManager.addUser(newUserDto);
        } catch (UnexpectedRollbackException e) {
            logger.warn("roll back on new user");
        }
        if (addNewUserIsOk) {
            return (new ResponseEntity<>(HttpStatus.CREATED));
        } else {
            return (new ResponseEntity<>("email already exist",HttpStatus.CONFLICT));
        }
    }

    /**
     * For check if user in progress is already valid
     * @return
     */
    @GetMapping(value = "/userStateChanged")
    public boolean userStateChange() {
        return true;
    }

    /**
     * to get all users Emails
     * @return list
     */
    @GetMapping(value = "/allUserEmails")
    public List<String> allUsersEmails() {
        return userManager.getAllUsersEmails();
    }

    /**
     * For update user
     * @param userDto
     * @return
     */
    @PostMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User userUpdated = userManager.updateUser(userDto);
        logger.info("user " + userUpdated.getEmail() + " updated.");
        return userMapper.fromUserToDto(userUpdated);
    }
}
