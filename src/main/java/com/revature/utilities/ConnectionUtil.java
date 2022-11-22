package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    public static Connection createConnection(){
        try {
            return DriverManager.getConnection(
                System.getenv("URL"), 
                System.getenv("USERNAME"), 
                System.getenv("PASSWORD")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // use this to confirm you set up your environment variables correctly
        System.out.println(createConnection());
    }

}
