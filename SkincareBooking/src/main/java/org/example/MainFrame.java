package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final User user;

    public MainFrame(User user) {
        this.user = user;

        setTitle("Hệ thống đặt lịch Skincare - Xin chào " + user.getFullName());
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new BackgroundPanel("images/theme.jpg"));

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ✅ Header chứa logo và chào user
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // 👉 Logo trái
        JLabel logoLabel = new JLabel();
        java.net.URL logoUrl = getClass().getClassLoader().getResource("images/logo.jpg");
        if (logoUrl != null) {
            ImageIcon logoIcon = new ImageIcon(logoUrl);
            logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // 👉 Chào user giữa (trắng)
        JLabel welcomeLabel = new JLabel("Xin chào, " + user.getFullName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ✅ Nút chức năng (hàng ngang)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setOpaque(false);

        JButton bookingButton = createFlatButton("Đặt lịch chăm sóc da");
        bookingButton.addActionListener(e -> new AppointmentForm(user).setVisible(true));

        JButton accountButton = createFlatButton("Quản lý tài khoản");
        accountButton.addActionListener(e -> {
            new AccountManagementFrame(user).setVisible(true);
        });

        JButton logoutButton = createFlatButton("Đăng xuất");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        buttonPanel.add(bookingButton);
        buttonPanel.add(accountButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // ✅ Panel liên hệ (dưới cùng)
        JPanel contactPanel = new JPanel(new GridLayout(2, 1));
        contactPanel.setOpaque(false);
        contactPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel addressLabel = new JLabel("Địa chỉ: Tô Ký, Quận 12, TP.HCM", SwingConstants.CENTER);
        JLabel phoneLabel = new JLabel("Điện thoại: 0909 123 456", SwingConstants.CENTER);

        addressLabel.setForeground(Color.WHITE);
        phoneLabel.setForeground(Color.WHITE);

        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        contactPanel.add(addressLabel);
        contactPanel.add(phoneLabel);

        add(contactPanel, BorderLayout.SOUTH);
    }

    // ✅ Tạo nút trong suốt, không viền
    private JButton createFlatButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
