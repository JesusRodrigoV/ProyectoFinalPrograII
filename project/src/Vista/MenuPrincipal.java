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

        Color normalBackground = contentPane.getBackground();
        Color verdeBoton = new Color(66, 245, 158);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 80, 50, 20, 0};
        gbl_contentPane.rowHeights = new int[]{20, 0, 20, 30, 5, 30, 5, 30, 30, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JLabel lblNewLabel = new JLabel("Bienvenido");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        ImagePanel panel = new ImagePanel("/resources/LogoBancoMundial.png");
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.gridheight = 7;
        gbc_panel.insets = new Insets(0, 0, 0, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        contentPane.add(panel, gbc_panel);

        JButton btnNewButton_1 = new JButton("Registro");
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setOpaque(false);
                    selectedButton.setBackground(normalBackground);
                    selectedButton.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
                btnNewButton_1.setOpaque(true);
                btnNewButton_1.setBackground(verdeBoton);
                btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
                selectedButton = btnNewButton_1;

                Menu app = new Menu();
                app.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnNewButton_1 != selectedButton) {
                    btnNewButton_1.setOpaque(true);
                    btnNewButton_1.setBackground(verdeBoton);
                    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnNewButton_1 != selectedButton) {
                    btnNewButton_1.setOpaque(false);
                    btnNewButton_1.setBackground(normalBackground);
                    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 5;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

        JButton btnNewButton = new JButton("Cuentas");
        btnNewButton.setFocusPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setOpaque(false);
                    selectedButton.setBackground(normalBackground);
                    selectedButton.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
                btnNewButton.setOpaque(true);
                btnNewButton.setBackground(verdeBoton);
                btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
                selectedButton = btnNewButton;

                Planes app = new Planes();
                app.setVisible(true);
                dispose();
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnNewButton != selectedButton) {
                    btnNewButton.setOpaque(true);
                    btnNewButton.setBackground(verdeBoton);
                    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnNewButton != selectedButton) {
                    btnNewButton.setOpaque(false);
                    btnNewButton.setBackground(normalBackground);
                    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 3;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_2 = new JButton("NewButton");
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setContentAreaFilled(false);
        btnNewButton_2.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setOpaque(false);
                    selectedButton.setBackground(normalBackground);
                    selectedButton.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
                btnNewButton_2.setOpaque(true);
                btnNewButton_2.setBackground(verdeBoton);
                btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
                selectedButton = btnNewButton_2;

                JOptionPane.showMessageDialog(contentPane, "Button Clicked");
            }
        });
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btnNewButton_2 != selectedButton) {
                    btnNewButton_2.setOpaque(true);
                    btnNewButton_2.setBackground(verdeBoton);
                    btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnNewButton_2 != selectedButton) {
                    btnNewButton_2.setOpaque(false);
                    btnNewButton_2.setBackground(normalBackground);
                    btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 2;
        gbc_btnNewButton_2.gridy = 7;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
    }
}
