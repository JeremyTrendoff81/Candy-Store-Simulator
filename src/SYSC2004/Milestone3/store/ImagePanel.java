package SYSC2004.Milestone3.store;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The ImagePanel class acts as a JPanel with a displayed image.
 *
 * @author Jeremy Trendoff - 101160306 and Evan Smedley - 101148695
 * @version 1.0
 * @since March 27, 2021
 */
public class ImagePanel extends JPanel {

    /**
     * The image to be displayed on the JPanel.
     */
    private BufferedImage image;

    /**
     * Constructor for ImagePanel.
     *
     * @param imageString   A String, the name of the image to be displayed.
     */
    public ImagePanel(String imageString) {
        try {
            image = ImageIO.read(this.getClass().getResource(imageString));
        } catch (Exception ex) {
            image = null;
        }
    }

    /**
     * Get the Panel's image.
     *
     * @return  A BufferedImage, the image being displayed.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Set the Panel's image.
     *
     * @param imageString  String, the name of the image to be displayed.
     */
    public void setImage(String imageString) {
        try {
            image = ImageIO.read(this.getClass().getResource(imageString));
        } catch (Exception ex) {
            image = null;
        }
    }

    /**
     * Display the Panel.
     *
     * @param g Graphics Object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
