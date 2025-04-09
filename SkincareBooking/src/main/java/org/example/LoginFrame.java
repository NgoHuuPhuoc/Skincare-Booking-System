package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Đăng nhập Skincare App");
        setSize(420, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel chính có nền ảnh
        BackgroundPanel mainPanel = new BackgroundPanel("images/themeloginout.jpg"); // <== thay đúng đường dẫn ảnh
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Đăng nhập");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Username
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("Tài khoản:");
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel, gbc);

        usernameField = new JTextField();
        styleTextField(usernameField);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Password
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setForeground(Color.WHITE);
        mainPanel.add(passLabel, gbc);

        passwordField = new JPasswordField();
        styleTextField(passwordField);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Nút đăng nhập
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(255, 204, 229));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        loginButton.addActionListener(new LoginAction());

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Nút đăng ký
        JButton registerButton = new JButton("Đăng ký tài khoản mới");
        registerButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        registerButton.setForeground(Color.WHITE);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
        });

        gbc.gridy++;
        mainPanel.add(registerButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true)); // Bo góc
    }

    // Xử lý đăng nhập
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công! Chào " + user.getFullName());
                new MainFrame(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
