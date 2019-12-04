package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.PasswordEncoder;
import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceDao.AddressDao;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entities.entity.Address;
import com.eSales.microserviceModel.entities.entity.User;
import com.eSales.microserviceModel.entities.dto.UserDto;
import com.eSales.microserviceModel.entities.mapper.contract.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        User userOnBdd = new User();
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
     * for create new user
     * @param userDto
     */
    @Transactional
    @Override
    public void addUser(UserDto userDto) {
        Address addressInput = new Address();
        Address newAddress = new Address();
        int addressId;
        User newUser = new User();

        // set new address // todo mapper d'adresse dto -> address
        addressInput.setStreet(userDto.getStreet());
        addressInput.setPostalCode(userDto.getPostalCode());
        addressInput.setCity(userDto.getCity());

        // creation d'une nouvelle addresse
        newAddress = addressDao.save(addressInput);

        // set new user
        newUser = userMapper.fromDtoToUserWithoutAddress(userDto);

        // ajout de l'adress dans le newUser
        newUser.setAddress(newAddress);

        // hashing du password en clair reçus du userDto
        String hashedPassword = passwordEncoder.hashPassword(userDto.getPassword());

        // je le rajoute dans newUser
        newUser.setPassword(hashedPassword);

        // je save le new user en bdd
        userDao.save(newUser);

    }

}
