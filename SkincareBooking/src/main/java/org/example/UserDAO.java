package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Role")
                );
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND PasswordHash = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Nếu có mã hóa MD5/SHA256, thì mã hóa trước khi set

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getString("Email"),
                            rs.getString("Fullname"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Role")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}