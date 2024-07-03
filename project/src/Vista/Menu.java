package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.*;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color rojo = new Color(255, 91, 91);
    private Color fondoNormal = Color.WHITE;
    private Color negro = Color.BLACK;
    private Color blanco = Color.white;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 259);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 155, 132, 0, 0};
        gbl_contentPane.rowHeights = new int[]{20, 25, 25, 0, 20, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JButton btnNewButton = new JButton("Registro");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register registro = new Register();
                registro.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 1;
        btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnNewButton.setBackground(fondoNormal);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setOpaque(true);
                btnNewButton.setBackground(new Color(0, 229, 118));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setOpaque(false);
                btnNewButton.setBackground(verdeBoton);
            }
            });
        
        ImagePanel panel = new ImagePanel("/resources/LogoBancoMundial.png");
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridheight = 3;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        contentPane.add(panel, gbc_panel);
        contentPane.add(btnNewButton, gbc_btnNewButton);
                
        JButton btnNewButton_1 = new JButton("Inicio de sesion");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 2;
        btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnNewButton_1.setBackground(fondoNormal);
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1.setOpaque(true);
                btnNewButton_1.setBackground(new Color(0, 229, 118));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1.setOpaque(false);
                btnNewButton_1.setBackground(verdeBoton);
            }
            });
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("Volver al Menu");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal princ = new MenuPrincipal();
                princ.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton_1_1.setFocusPainted(false);
        btnNewButton_1_1.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        
        GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
        gbc_btnNewButton_1_1.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1_1.gridx = 2;
        gbc_btnNewButton_1_1.gridy = 3;
        btnNewButton_1_1.setBackground(fondoNormal);
        contentPane.add(btnNewButton_1_1, gbc_btnNewButton_1_1);
        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1_1.setOpaque(true);
                btnNewButton_1_1.setBackground(rojo);
                btnNewButton_1_1.setForeground(blanco);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1_1.setOpaque(false);
                btnNewButton_1_1.setBackground(fondoNormal);
                btnNewButton_1_1.setForeground(negro);
            }
        });
    }
}
