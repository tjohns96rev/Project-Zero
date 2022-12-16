package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

public class UserDao {

    public User getUserByUsername(String username) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt(1));
            user.setUsername(username);
            user.setPassword(rs.getString(3));
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sqlStatement = "insert into users values(default,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, registerRequest.getUsername());
            ps.setString(2, registerRequest.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            User newUser = new User();
            rs.next();
            newUser.setId(rs.getInt("id"));
            newUser.setUsername(rs.getString("username"));
            newUser.setPassword(rs.getString("password"));
            return newUser;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }
}
