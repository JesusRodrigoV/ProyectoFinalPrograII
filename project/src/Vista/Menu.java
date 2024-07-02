package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
  //private final Color hoverBackground = new Color(0, 168, 107);
    private final Color verdeBoton = new Color(66, 245, 158);
    private final Color rojo = new Color(255, 91, 91);
    private final Color fondoNormal = Color.WHITE;
    private final Color negro = Color.BLACK;
    private final Color blanco = Color.white;
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
        gbl_contentPane.columnWidths = new int[]{0, 155, 77, 132, 0, 0};
        gbl_contentPane.rowHeights = new int[]{56, 25, 45, 25, 23, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
                gbc_btnNewButton.gridx = 3;
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
                contentPane.add(btnNewButton, gbc_btnNewButton);
                
                        JLabel lblNewLabel = new JLabel("BANCO MUNDIAL");
                        lblNewLabel.setFont(new Font("Verdana", Font.ITALIC, 15));
                        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
                        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
                        gbc_lblNewLabel.gridx = 1;
                        gbc_lblNewLabel.gridy = 2;
                        contentPane.add(lblNewLabel, gbc_lblNewLabel);
                
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
                        gbc_btnNewButton_1.gridx = 3;
                        gbc_btnNewButton_1.gridy = 3;
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
                        gbc_btnNewButton_1_1.insets = new Insets(0, 0, 0, 5);
                        gbc_btnNewButton_1_1.gridx = 3;
                        gbc_btnNewButton_1_1.gridy = 5;
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
