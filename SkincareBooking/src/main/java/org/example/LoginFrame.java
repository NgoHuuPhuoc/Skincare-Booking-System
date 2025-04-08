package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Đăng nhập hệ thống Skincare Booking");
        setSize(400, 250);
        setLocationRelativeTo(null); // Căn giữa
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Panel cho Username
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        userPanel.add(usernameField);
        add(userPanel);

        // Panel cho Password
        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        passPanel.add(passwordField);
        add(passPanel);

        // Nút Đăng nhập
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new LoginAction());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        add(buttonPanel);
    }

    // Xử lý khi nhấn nút
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "✅ Đăng nhập thành công! Chào " + user.getFullname());

                // Mở màn hình chính
                MainFrame mainFrame = new MainFrame(user);
                mainFrame.setVisible(true);

                // Đóng LoginFrame
                SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
            } else {

                JOptionPane.showMessageDialog(null, "❌ Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Chạy chương trình
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
