package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagenUtils {

    public static ImageIcon getScaledImageIcon(String imagePath, JLabel label) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        // Obtener el tamaño actual del JLabel
        int width = label.getWidth();
        int height = label.getHeight();

        // Crear una imagen BufferedImage para dibujar y escalar la imagen original
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();

        // Escalar la imagen original al tamaño del JLabel utilizando Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();  // Libera los recursos del contexto gráfico

        // Devuelve un nuevo ImageIcon creado a partir de la imagen escalada
        return new ImageIcon(scaledImage);
    }
}

