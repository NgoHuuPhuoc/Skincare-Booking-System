package org.example;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ ảnh nền khớp kích thước panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
