package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home_Gui extends JPanel {
    //img/backgr.jpeg
    public Home_Gui() {
        JLabel imageLabel = new JLabel();
        this.add(imageLabel);

        // Tải hình ảnh và hiển thị nó trong JLabel
        try {
            BufferedImage image = ImageIO.read(new File("img/backgr1.jpg"));

            ImageIcon icon = new ImageIcon(image);

            imageLabel.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
