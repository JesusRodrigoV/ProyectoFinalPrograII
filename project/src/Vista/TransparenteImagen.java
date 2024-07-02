package Vista;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TransparenteImagen extends JPanel {
    private BufferedImage image;

    public TransparenteImagen(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.WHITE); // Aseguramos que el fondo sea blanco
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Dibujar la imagen ajustada al tamaño del panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            float widthRatio = (float) panelWidth / imageWidth;
            float heightRatio = (float) panelHeight / imageHeight;
            float ratio = Math.min(widthRatio, heightRatio);

            int newWidth = (int) (imageWidth * ratio);
            int newHeight = (int) (imageHeight * ratio);

            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }
}

