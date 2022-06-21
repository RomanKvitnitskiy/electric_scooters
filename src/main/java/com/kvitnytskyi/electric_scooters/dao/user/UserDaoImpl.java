package com.kvitnytskyi.electric_scooters.dao.user;

import com.kvitnytskyi.electric_scooters.connection.BasicConnectionPool;
import com.kvitnytskyi.electric_scooters.dao.QUERY;
import com.kvitnytskyi.electric_scooters.model.security.BCryptEncryptor;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.util.UserUtil;
import org.apache.log4j.Logger;
/*import sun.reflect.generics.reflectiveObjects.NotImplementedException;*/


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance;
    private static BCryptEncryptor crypt;
    private static BasicConnectionPool connectionPool;
    private Connection connection;

    private UserDaoImpl() {
        try {
            connectionPool = BasicConnectionPool.create();
            connection = connectionPool.getConnection();

            crypt = new BCryptEncryptor();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User save(User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_USER.query(),
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, crypt.encryptPassword(user.getPassword()));
            statement.setBoolean(6, user.isStatus());
            statement.setLong(7, user.getUserRole().ordinal() + 1);
            statement.setDouble(8, user.getBalance());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("User creation is failed");
            } else {
                log.info("User creation is successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        User.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create user, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            user = User.builder().id(-1).build();
            log.error(exception);
        }
        return user;
    }

    @Override
    public void update(User user, String[] params) {
        throw new IllegalArgumentException();
    }

    @Override
    public User get(long id) {
        try (PreparedStatement statement = connection.prepareStatement(QUERY.GET_USER.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public User checkLogin(String login) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CHECK_USER.query())) {
            statement.setString(1, login);
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        throw new IllegalArgumentException();
    }

    @Override
    public List<User> getAll() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_USERS.query());
            return getUsersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        log.info("Search for user by login and password");

        try (PreparedStatement statement = connection
                .prepareStatement(QUERY.GET_USER_BY_LOGIN_AND_PASSWORD.query())) {
            statement.setString(1, login);
            statement.setString(2, crypt.encryptPassword(password));
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public boolean topUpBalance(long userId, double balance) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.TOP_UP.query())) {
            statement.setDouble(1, balance);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.info("Top up was failed");
                return false;
            } else {
                log.info("Top up was successful");
                return true;
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return false;
    }

    @Override
    public boolean changeStatus(long userId, boolean status) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CHANGE_USER_STATUS.query())) {
            statement.setBoolean(1, status);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.info("Change status was failed");
                return false;
            } else {
                log.info("Change status was successful");
                return true;
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return false;
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return buildUserFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private List<User> getUsersFromResultSet(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                users.add(buildUserFromResultSet(resultSet));
                log.info("User was found and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return users;
    }

    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("User was found and packed in object");
        return UserUtil.createUserFromResultSet(resultSet);
    }

    /*private String checkPassword(String password) {

        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_PASSWORD.query())) {
            statement.setString(1, crypt.encryptPassword(password));
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.info("Change status was failed");
                return password;
            } else {
                log.info("Change status was successful");
                return checkPassword(password);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }*/
}
