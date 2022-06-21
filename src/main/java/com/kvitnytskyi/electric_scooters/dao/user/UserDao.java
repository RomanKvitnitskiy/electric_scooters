package com.kvitnytskyi.electric_scooters.dao.user;

import com.kvitnytskyi.electric_scooters.dao.DAO;
import com.kvitnytskyi.electric_scooters.model.user.User;


public interface UserDao extends DAO<User> {

    User getUserByLoginAndPassword(String login, String password);

    boolean topUpBalance(long userId, double balance);

    boolean changeStatus(long userId, boolean status);

    User checkLogin(String s);

}
