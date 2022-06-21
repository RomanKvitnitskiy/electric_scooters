package com.kvitnytskyi.electric_scooters.service.user;

import com.kvitnytskyi.electric_scooters.dao.user.UserDao;
import com.kvitnytskyi.electric_scooters.model.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean signUp(User user) {
        log.info("Signup new user");
        return user != null && userDao.save(user).getId() != -1;
    }

    public User getUserByCredentials(String login, String password) {
        log.info("Retrieving user from database by email and password");
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public List<User> getAllUsers() {
        log.info("Retrieving all users from database");
        return userDao.getAll();
    }

    public User getUserById(long userId) {
        log.info("Retrieving user by id");
        return userDao.get(userId);
    }

    public boolean topUpUserBalance(long userId, double balance) {
        log.info("Top up user balance by user id");
        return userDao.topUpBalance(userId, balance);
    }

    public boolean changeUserStatus(long userId, boolean status) {
        log.info("Change user status by id and status");
        return userDao.changeStatus(userId, status);
    }

    public User checkUserLogin(String login) {
        log.info("Checking user login for unique");
        return userDao.checkLogin(login);
    }
}
