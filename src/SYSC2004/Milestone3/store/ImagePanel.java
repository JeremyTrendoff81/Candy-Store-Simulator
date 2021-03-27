package SYSC2004.Milestone3.store;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(String imageString) {
        try {
            image = ImageIO.read(this.getClass().getResource(imageString));
        } catch (Exception ex) {
            image = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
