package com.kvitnytskyi.electric_scooters.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionCreator {

    private static DataSource dataSource = null;
    
    static {
        System.out.println("new con");
        Context initialContext = null;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/electric_scooters");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    private ConnectionCreator() {}

    public static Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
