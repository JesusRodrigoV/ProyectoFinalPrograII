package Controlador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vista.Login;
import Vista.Register;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

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
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 255, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("BANCO MUNDIAL");
        lblNewLabel.setFont(new Font("Verdana", Font.ITALIC, 15));
        lblNewLabel.setBounds(20, 79, 155, 45);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Registro");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register registro = new Register();
                registro.setVisible(true);
            }
        });
        btnNewButton.setBounds(273, 56, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Inicio de sesion");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(252, 159, 132, 23);
        contentPane.add(btnNewButton_1);
    }
}
