package com.esales.microservicebusiness.impl;

import com.esales.microservicebusiness.contract.PasswordEncoder;
import com.esales.microservicebusiness.contract.UserManager;
import com.esales.microservicedao.AddressDao;
import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.mapper.contract.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);


    /**
     * for check if User Mail and Password is valid
     * @param userToValidate
     * @return
     */
    @Override
    public boolean checkIfUserMailAndPasswordIsOk(User userToValidate) {
        User userOnBdd;
        boolean mailExist = false;
        boolean passwordIsValid = false;
        boolean mailAndUserExist = false;

        // check if email exist on bdd
        mailExist = this.checkIfMailExist(userToValidate.getEmail());

        if (mailExist) {
            // get user on bdd
            userOnBdd = userDao.findByEmail(userToValidate.getEmail());
            //compare password
            passwordIsValid = passwordEncoder.checkPassword(userToValidate.getPassword(), userOnBdd.getPassword());
            if (passwordIsValid) {
                mailAndUserExist = true;
                logger.info("L'utilisateur " + userToValidate.getEmail() + " est validé.");
            } else {
                logger.info("L'utilisateur " + userToValidate.getEmail() + " n'a pas rentré le bon mot de passe.");
            }
        }
        return  mailAndUserExist;
    }

    /**
     * for check if mail exist on bdd
     * @param email
     * @return
     */
    @Override
    public boolean checkIfMailExist(String email) {
        boolean mailExist = false;

        User oneUser = userDao.findByEmail(email);
        if (oneUser != null) {
            mailExist = true;
        } else {
            logger.info("L'utilisateur: " + email + " n'existe pas en BDD.");
        }
        return mailExist;
    }

    /**
     * for create new user (user address and user)
     * @param userDto
     */
    @Transactional
    @Override
    public boolean addUser(UserDto userDto) {
        Address addressInputFromUserDto;
        Address newAddress;
        User newUserfromDto;

        // new address -> bdd
        addressInputFromUserDto = userMapper.fromUserDtoToAddress(userDto);

        newAddress = addressDao.save(addressInputFromUserDto);

        // new user -> bdd
        newUserfromDto = userMapper.fromDtoToUserWithoutAddress(userDto);
        newUserfromDto.setAddress(newAddress);
        String hashedPassword = passwordEncoder.hashPassword(userDto.getPassword());
        newUserfromDto.setPassword(hashedPassword);

        try {
            userDao.save(newUserfromDto);
        } catch (DataIntegrityViolationException e) {
            logger.info("L'enregistrement du nouvel utilisateur" + userDto.getEmail() + " à échoué: l'email existe déjà en BDD");
            return false;
        }
        return true;
    }

    /**
     * to get all user from bdd
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * to get user bean with one mail
     * @param email
     * @return
     */
    @Override
    public User findUserByMail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * to get all users Emails
     * @return list
     */
    @Override
    public List<String> getAllUsersEmails() {
        return userDao.getAllUsersEmails();
    }
}
