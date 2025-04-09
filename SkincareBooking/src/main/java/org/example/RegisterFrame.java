package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterFrame extends JFrame {
    private JTextField fullnameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterFrame() {
        setTitle("📝 Đăng ký tài khoản");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 245, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel titleLabel = new JLabel("Đăng ký tài khoản mới");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel("👩‍💼 Họ tên:"), gbc);

        fullnameField = new JTextField();
        fullnameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(fullnameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("👤 Tài khoản:"), gbc);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("🔒 Mật khẩu:"), gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("🔒 Xác nhận mật khẩu:"), gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(confirmPasswordField, gbc);

        JButton registerButton = new JButton("✅ Đăng ký");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setBackground(new Color(255, 204, 229));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        registerButton.addActionListener(this::handleRegister);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void handleRegister(ActionEvent e) {
        String fullname = fullnameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirm = new String(confirmPasswordField.getPassword()).trim();

        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "❌ Mật khẩu xác nhận không khớp!");
            return;
        }

        String hashedPassword = hashPassword(password);
        User user = new User(0, username, hashedPassword, "", fullname, "", "user");

        UserDAO dao = new UserDAO();
        if (dao.register(user)) {
            JOptionPane.showMessageDialog(this, "🎉 Đăng ký thành công!");
            this.dispose();

            // Mở LoginFrame kèm hiệu ứng mờ
            SwingUtilities.invokeLater(() -> {
                LoginFrame login = new LoginFrame();
                login.setUndecorated(true); // bắt buộc cho opacity
                login.setBackground(new Color(0, 0, 0, 0));
                login.setOpacity(0.0f);
                login.setVisible(true);
                new Timer(10, new AbstractAction() {
                    float opacity = 0.0f;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        opacity += 0.05f;
                        login.setOpacity(Math.min(opacity, 1.0f));
                        if (opacity >= 1.0f) {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                }).start();
            });

        } else {
            JOptionPane.showMessageDialog(this, "❌ Tài khoản đã tồn tại hoặc lỗi khi đăng ký.");
        }
    }

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
