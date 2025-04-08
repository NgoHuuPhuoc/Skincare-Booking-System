package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final User user;

    public MainFrame(User user) {
        this.user = user;

        setTitle("Hệ thống đặt lịch Skincare - Xin chào " + user.getFullname());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI(); // Gọi hàm khởi tạo UI
    }

    private void initUI() {
        // Panel chính
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("🎉 Xin chào, " + user.getFullname(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // 👉 Nút Đặt lịch
        JButton bookingButton = new JButton("Đặt lịch");
        bookingButton.addActionListener(e -> {
            AppointmentForm form = new AppointmentForm(user);
            form.setVisible(true);
        });

        // Các nút khác
        JButton accountButton = new JButton("Quản lý tài khoản");
        JButton logoutButton = new JButton("Đăng xuất");

        // Thêm các nút vào panel
        buttonPanel.add(bookingButton);
        buttonPanel.add(accountButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Thêm panel vào frame
        add(panel);
    }
}
