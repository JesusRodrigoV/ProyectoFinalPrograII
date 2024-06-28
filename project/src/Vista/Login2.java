package Vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Login2 {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login2 window = new Login2();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblNewLabel_1 = new JLabel();
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

        // Cargar y escalar la imagen
        ImageIcon scaledIcon = getScaledImageIcon("/resources/2 (2).png", 531, 197);
        if (scaledIcon != null) {
            lblNewLabel_1.setIcon(scaledIcon);
        } else {
            lblNewLabel_1.setText("Error al cargar la imagen");
        }

        JLabel lblNewLabel = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
    }

    // Método para escalar la imagen al tamaño deseado con mejor calidad
    private ImageIcon getScaledImageIcon(String imagePath, int width, int height) {
        try (InputStream stream = getClass().getResourceAsStream(imagePath)) {
            if (stream != null) {
                BufferedImage originalImage = ImageIO.read(stream);

                // Escalar la imagen
                BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = scaledImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.drawImage(originalImage, 0, 0, width, height, null);
                g2d.dispose();

                return new ImageIcon(scaledImage);
            } else {
                System.err.println("No se pudo cargar la imagen: " + imagePath);
                return null; // O manejar el error de otra manera
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
