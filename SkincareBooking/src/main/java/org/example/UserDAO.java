package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                        rs.getString("FullName"),
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

            String hashedPassword = hashPassword(password);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getString("Email"),
                            rs.getString("FullName"),
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

    public boolean register(User user) {
        String checkSql = "SELECT * FROM Users WHERE Username = ?";
        String insertSql = "INSERT INTO Users (Username, PasswordHash, Email, FullName, PhoneNumber, Role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            checkStmt.setString(1, user.getUsername());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false;
            }

            insertStmt.setString(1, user.getUsername());
            insertStmt.setString(2, user.getPasswordHash());
            insertStmt.setString(3, user.getEmail());
            insertStmt.setString(4, user.getFullName());
            insertStmt.setString(5, user.getPhoneNumber());
            insertStmt.setString(6, user.getRole() != null ? user.getRole() : "user");

            return insertStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Cập nhật thông tin người dùng (không đổi mật khẩu)
    public boolean updateUserInfo(User user) {
        String sql = "UPDATE Users SET FullName = ?, Email = ?, PhoneNumber = ? WHERE UserID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setInt(4, user.getUserID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Đổi mật khẩu có kiểm tra mật khẩu cũ
    public boolean changePassword(int userID, String oldPass, String newPass) {
        String checkSql = "SELECT PasswordHash FROM Users WHERE UserID = ?";
        String updateSql = "UPDATE Users SET PasswordHash = ? WHERE UserID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, userID);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String currentHash = rs.getString("PasswordHash");
                String oldHash = hashPassword(oldPass);

                if (!currentHash.equals(oldHash)) {
                    return false; // ❌ Mật khẩu cũ không đúng
                }

                String newHash = hashPassword(newPass);
                updateStmt.setString(1, newHash);
                updateStmt.setInt(2, userID);

                return updateStmt.executeUpdate() > 0; // ✅ Thành công
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Hàm mã hóa SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Lỗi mã hóa mật khẩu", ex);
        }
    }
}
