package org.example;

public class Main {
    public static void main(String[] args) {
        // 👉 Tạo user demo đúng với constructor: int, String, String, ...
        User demoUser = new User(
                1,
                "Nguyễn Văn A",
                "vana@gmail.com",
                "0909123456",
                "123456",
                "Nam",
                "Khách hàng"
        );

        // 👉 Mở giao diện chính
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(demoUser);
            frame.setVisible(true);
        });
    }
}
