package org.example;

import javax.swing.*;
import java.awt.*;

public class AccountManagementFrame extends JFrame {
    private final User user;

    public AccountManagementFrame(User user) {
        this.user = user;
        setTitle("Quản lý tài khoản");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Đặt panel nền
        BackgroundPanel backgroundPanel = new BackgroundPanel("images/account.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);

        JPanel infoPanel = createInfoPanel();
        JPanel passwordPanel = createPasswordPanel();

        tabbedPane.addTab("Thông tin cá nhân", infoPanel);
        tabbedPane.addTab("Đổi mật khẩu", passwordPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Họ tên:");
        JTextField nameField = new JTextField(user.getFullName());

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(user.getEmail());

        JLabel phoneLabel = new JLabel("Số điện thoại:");
        JTextField phoneField = new JTextField(user.getPhoneNumber());

        JButton saveButton = createFlatButton("Lưu thông tin");
        saveButton.addActionListener(e -> {
            user.setFullName(nameField.getText().trim());
            user.setEmail(emailField.getText().trim());
            user.setPhoneNumber(phoneField.getText().trim());

            if (new UserDAO().updateUserInfo(user)) {
                JOptionPane.showMessageDialog(this, "✅ Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Cập nhật thất bại.");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; panel.add(nameLabel, gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1; panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1; panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        return panel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel oldPassLabel = new JLabel("Mật khẩu cũ:");
        JPasswordField oldPassField = createPasswordField();

        JLabel newPassLabel = new JLabel("Mật khẩu mới:");
        JPasswordField newPassField = createPasswordField();

        JLabel confirmPassLabel = new JLabel("Xác nhận mật khẩu:");
        JPasswordField confirmPassField = createPasswordField();

        JButton changeButton = createFlatButton("Đổi mật khẩu");
        changeButton.addActionListener(e -> {
            String oldPass = new String(oldPassField.getPassword());
            String newPass = new String(newPassField.getPassword());
            String confirmPass = new String(confirmPassField.getPassword());

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "❌ Xác nhận mật khẩu không khớp.");
                return;
            }

            if (new UserDAO().changePassword(user.getUserID(), oldPass, newPass)) {
                JOptionPane.showMessageDialog(this, "✅ Đổi mật khẩu thành công.");
                oldPassField.setText("");
                newPassField.setText("");
                confirmPassField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Mật khẩu cũ không đúng hoặc có lỗi xảy ra.");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; panel.add(oldPassLabel, gbc);
        gbc.gridx = 1; panel.add(oldPassField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(newPassLabel, gbc);
        gbc.gridx = 1; panel.add(newPassField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(confirmPassLabel, gbc);
        gbc.gridx = 1; panel.add(confirmPassField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(changeButton, gbc);

        return panel;
    }

    private JButton createFlatButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setForeground(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 28));
        return field;
    }

    // JPanel với ảnh nền
    static class BackgroundPanel extends JPanel {
        private final Image background;

        public BackgroundPanel(String imagePath) {
            this.background = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(
                        background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH),
                        0, 0, getWidth(), getHeight(), this
                );
            }
        }
    }
}
