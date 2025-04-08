package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Đảm bảo giao diện chạy đúng trên EDT
        SwingUtilities.invokeLater(() -> {
            UserDAO userDAO = new UserDAO();

            // Test login
            User loggedIn = userDAO.login("nguyenvana", "123456");
            if (loggedIn != null) {
                System.out.println("Đăng nhập thành công: " + loggedIn.getFullname());

                // 👉 Mở giao diện chính
                MainFrame mainFrame = new MainFrame(loggedIn);

                // ✅ Gán logo app (ảnh logo.png phải nằm trong src/main/resources/images/)
                try {
                    ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/logo.jpg"));
                    mainFrame.setIconImage(logoIcon.getImage());
                } catch (Exception e) {
                    System.out.println("⚠️ Không tìm thấy ảnh logo. Đảm bảo ảnh nằm trong thư mục /resources/images/");
                }

                mainFrame.setVisible(true);
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu.");
                JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu.");
            }
        });
    }
}
