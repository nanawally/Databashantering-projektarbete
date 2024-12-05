package se.anna.employeedatabase.utility;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCUtil {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream stream =
                    JDBCUtil.class.getClassLoader().
                            getResourceAsStream("application.properties");
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        Driver driver = new org.hsqldb.jdbcDriver();
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        System.out.println(url);
        System.out.println(user);
        System.out.println(password);

        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
