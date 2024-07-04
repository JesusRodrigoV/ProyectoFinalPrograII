package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton selectedButton = null;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
    private Color rojo = new Color(255, 91, 91);
    private Color negro = Color.BLACK;
    private Color blanco = Color.white;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuPrincipal frame = new MenuPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 517, 293);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 80, 50, 20, 0};
        gbl_contentPane.rowHeights = new int[]{20, 0, 20, 30, 5, 30, 5, 30, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JLabel lblNewLabel = new JLabel("Bienvenido");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        ImagePanel panel = new ImagePanel("/resources/LogoBancoMundial.png");
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.gridheight = 6;
        gbc_panel.insets = new Insets(0, 0, 0, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        contentPane.add(panel, gbc_panel);

        JButton btnRegistro = new JButton("Clientes");
        btnRegistro.setFocusPainted(false);
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setOpaque(false);
                    selectedButton.setBackground(blanco);
                    selectedButton.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
                btnRegistro.setOpaque(true);
                btnRegistro.setBackground(verdeBoton);
                btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
                selectedButton = btnRegistro;

                Menu app = new Menu();
                app.setVisible(true);
                dispose();
            }
        });
        btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnRegistro != selectedButton) {
                    btnRegistro.setOpaque(true);
                    btnRegistro.setBackground(verdeBoton);
                    btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnRegistro != selectedButton) {
                    btnRegistro.setOpaque(false);
                    btnRegistro.setBackground(blanco);
                    btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
        gbc_btnRegistro.insets = new Insets(0, 0, 5, 5);
        gbc_btnRegistro.fill = GridBagConstraints.BOTH;
        gbc_btnRegistro.gridx = 2;
        gbc_btnRegistro.gridy = 5;
        contentPane.add(btnRegistro, gbc_btnRegistro);

        JButton btnCuentas = new JButton("Cuentas");
        btnCuentas.setFocusPainted(false);
        btnCuentas.setContentAreaFilled(false);
        btnCuentas.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnCuentas.setHorizontalAlignment(SwingConstants.CENTER);
        btnCuentas.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCuentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setOpaque(false);
                    selectedButton.setBackground(blanco);
                    selectedButton.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
                btnCuentas.setOpaque(true);
                btnCuentas.setBackground(verdeBoton);
                btnCuentas.setFont(new Font("Tahoma", Font.BOLD, 10));
                selectedButton = btnCuentas;

                Planes app = new Planes();
                app.setVisible(true);
                dispose();
            }
        });
        btnCuentas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnCuentas != selectedButton) {
                    btnCuentas.setOpaque(true);
                    btnCuentas.setBackground(verdeBoton);
                    btnCuentas.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnCuentas != selectedButton) {
                    btnCuentas.setOpaque(false);
                    btnCuentas.setBackground(blanco);
                    btnCuentas.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        GridBagConstraints gbc_btnCuentas = new GridBagConstraints();
        gbc_btnCuentas.fill = GridBagConstraints.BOTH;
        gbc_btnCuentas.insets = new Insets(0, 0, 5, 5);
        gbc_btnCuentas.gridx = 2;
        gbc_btnCuentas.gridy = 3;
        contentPane.add(btnCuentas, gbc_btnCuentas);
    }

    private void entraMouse(JButton boton){
        boton.setOpaque(true);
        boton.setBackground(verdeBoton);
        boton.setFont(new Font("Tahoma", Font.BOLD, 10));
    }
    private void saleMouse(JButton boton){
        boton.setOpaque(false);
        boton.setBackground(blanco);
        boton.setFont(new Font("Tahoma", Font.BOLD, 11));
    }
}
