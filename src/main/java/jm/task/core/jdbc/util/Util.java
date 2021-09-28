package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pptasks";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "FFF8rkfccybwf";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
