package org.alexsv.parcialfinal.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/BCN"; //00041923 get the URL connection to the database
    private static final String user = "root"; //00041923 get the user
    private static final String password = "*******"; //00041923 get the password
    //change the password for use it ↑
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password); //00041923 return the connection with the database
    }
}
