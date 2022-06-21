package com.kvitnytskyi.electric_scooters.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ConnectionPool {

    Connection getConnection() throws SQLException;

    boolean releaseConnection(Connection connection);

    List<Connection> getConnectionPool();

    int getSize();

    void shutdown() throws SQLException;
}
