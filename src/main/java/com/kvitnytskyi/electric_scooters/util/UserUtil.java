package com.kvitnytskyi.electric_scooters.util;

import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.model.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtil {

    public static void putToSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        switch (user.getUserRole()) {
            case USER:
                session.setAttribute("role", "user");
                break;
            case MANAGER:
                session.setAttribute("role", "manager");
                break;
            case ADMIN:
                session.setAttribute("role", "admin");
                break;
        }
        session.setAttribute("authorized", true);
    }

    public static User createUser(String[] user) {
        return User.builder()
                .name(user[0])
                .surname(user[1])
                .email(user[2])
                .login(user[3])
                .password(user[4])
                .status(true)
                .userRole(UserRole.USER)
                .build();
    }

    public static User createManger(String[] manager) {
        return User.builder()
                .name(manager[0])
                .surname(manager[1])
                .email(manager[2])
                .login(manager[3])
                .password(manager[4])
                .status(true)
                .userRole(UserRole.MANAGER)
                .build();
    }

    public static User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("user_name"))
                .surname(resultSet.getString("user_surname"))
                .email(resultSet.getString("email"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .status(resultSet.getBoolean("user_status"))
                .balance(resultSet.getDouble("user_balance"))
                .userRole(UserRole.values()[resultSet.getInt("role_id") - 1])
                .build();
    }

    public static String[] getRegistrationFields(HttpServletRequest req) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        return new String[]{name, surname, email, login, password, confirm_password};
    }
}
