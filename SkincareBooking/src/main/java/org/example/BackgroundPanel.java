package org.example;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // ✅ Constructor nhận đường dẫn ảnh (từ thư mục resources/images)
    public BackgroundPanel(String imagePath) {
        java.net.URL location = getClass().getClassLoader().getResource(imagePath);

        if (location != null) {
            System.out.println("✅ Đã tìm thấy ảnh tại: " + location);
            backgroundImage = new ImageIcon(location).getImage();
        } else {
            System.out.println("❌ Không tìm thấy ảnh: " + imagePath);
            System.out.println("📌 Gợi ý: Đặt ảnh trong src/main/resources/" + imagePath);
        }
    }

    // ✅ Constructor nhận trực tiếp đối tượng Image
    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
