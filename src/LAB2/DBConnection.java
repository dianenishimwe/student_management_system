package LAB2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school_db","postgres","123");

    return conn;
}}