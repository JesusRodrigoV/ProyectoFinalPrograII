package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorPerfil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea Nombres;
	private int id;
    private int id_cuenta;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
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
					Perfil frame = new Perfil(0,0);
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
	public Perfil(int id, int id_cuenta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 70, 100, 70, 20, 0};
		gbl_contentPane.rowHeights = new int[]{33, 13, 20, 13, 13, 13, 13, 13, 25, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton Cambio = new JButton("Cambiar datos");
		Cambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opciones = {"Usuario", "Contraseña"};
				int op = JOptionPane.showOptionDialog(null, "Seleccione una opción para cambiar:", "Cambio de datos",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

			if (op == 0) {
				CambioUsuario user = new CambioUsuario();
				user.setVisible(true);
			} else{
				CambioContrasena contra  = new CambioContrasena();
				contra.setVisible(true);
			}
			}
		});
		GridBagConstraints gbc_Cambio = new GridBagConstraints();
		gbc_Cambio.anchor = GridBagConstraints.SOUTHEAST;
		gbc_Cambio.insets = new Insets(0, 0, 5, 5);
		gbc_Cambio.gridx = 3;
		gbc_Cambio.gridy = 0;
		Cambio.setHorizontalAlignment(SwingConstants.CENTER);
		Cambio.setFocusPainted(false);
		Cambio.setBorder(new MatteBorder(0, 0, 0, 0, verdeOscuro));
		Cambio.setBackground(fondoNormal);
		Cambio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	Cambio.setOpaque(true);
                Cambio.setForeground(rojo);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Cambio.setOpaque(false);
            	Cambio.setForeground(negro);
            }
        });
		contentPane.add(Cambio, gbc_Cambio);
		
		JLabel lblNewLabel = new JLabel("Datos Personales");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(verdeOscuro);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres:");
		lblNewLabel_1.setForeground(verdeOscuro);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		Nombres = new JTextArea();
		Nombres.setEditable(false);
		GridBagConstraints gbc_nombres = new GridBagConstraints();
		gbc_nombres.gridwidth = 2;
		gbc_nombres.insets = new Insets(0, 0, 5, 5);
		gbc_nombres.fill = GridBagConstraints.BOTH;
		gbc_nombres.gridx = 2;
		gbc_nombres.gridy = 3;
		contentPane.add(Nombres, gbc_nombres);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellidos:");
		lblNewLabel_1_1.setForeground(verdeOscuro);
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 4;
		contentPane.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JTextArea Apellidos = new JTextArea();
		Apellidos.setEditable(false);
		GridBagConstraints gbc_Apellidos = new GridBagConstraints();
		gbc_Apellidos.gridwidth = 2;
		gbc_Apellidos.insets = new Insets(0, 0, 5, 5);
		gbc_Apellidos.fill = GridBagConstraints.BOTH;
		gbc_Apellidos.gridx = 2;
		gbc_Apellidos.gridy = 4;
		contentPane.add(Apellidos, gbc_Apellidos);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cedula:");
		lblNewLabel_1_1_1.setForeground(verdeOscuro);
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 1;
		gbc_lblNewLabel_1_1_1.gridy = 5;
		contentPane.add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JTextArea Cedula = new JTextArea();
		Cedula.setEditable(false);
		GridBagConstraints gbc_Cedula = new GridBagConstraints();
		gbc_Cedula.gridwidth = 2;
		gbc_Cedula.insets = new Insets(0, 0, 5, 5);
		gbc_Cedula.fill = GridBagConstraints.BOTH;
		gbc_Cedula.gridx = 2;
		gbc_Cedula.gridy = 5;
		contentPane.add(Cedula, gbc_Cedula);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Telefono:");
		lblNewLabel_1_1_1_1.setForeground(verdeOscuro);
		GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1_1_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1.gridx = 1;
		gbc_lblNewLabel_1_1_1_1.gridy = 6;
		contentPane.add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
		
		JTextArea Telefono = new JTextArea();
		Telefono.setEditable(false);
		GridBagConstraints gbc_Telefono = new GridBagConstraints();
		gbc_Telefono.gridwidth = 2;
		gbc_Telefono.insets = new Insets(0, 0, 5, 5);
		gbc_Telefono.fill = GridBagConstraints.BOTH;
		gbc_Telefono.gridx = 2;
		gbc_Telefono.gridy = 6;
		contentPane.add(Telefono, gbc_Telefono);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Fecha Nacimiento:");
		lblNewLabel_1_1_1_1_1.setForeground(verdeOscuro);
		GridBagConstraints gbc_lblNewLabel_1_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1_1_1_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1_1.gridx = 1;
		gbc_lblNewLabel_1_1_1_1_1.gridy = 7;
		contentPane.add(lblNewLabel_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1);
		
		JTextArea FechaNac = new JTextArea();
		FechaNac.setEditable(false);
		GridBagConstraints gbc_FechaNac = new GridBagConstraints();
		gbc_FechaNac.gridwidth = 2;
		gbc_FechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_FechaNac.fill = GridBagConstraints.BOTH;
		gbc_FechaNac.gridx = 2;
		gbc_FechaNac.gridy = 7;
		contentPane.add(FechaNac, gbc_FechaNac);
		
		ControladorPerfil control = new ControladorPerfil(id);
		ArrayList<String> datos = control.datos();
		Apellidos.setText(datos.get(2));
		Nombres.setText(datos.get(3));
		Cedula.setText(datos.get(4));
		Telefono.setText(datos.get(5));
		FechaNac.setText(datos.get(6));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setFocusPainted(false);
		btnSalir.setBorder(new MatteBorder(1, 0, 0, 0, rojo));
		btnSalir.setBackground(fondoNormal);
		btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnSalir.setOpaque(true);
            	btnSalir.setBackground(rojo);
				btnSalir.setForeground(blanco);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnSalir.setOpaque(false);
				btnSalir.setBackground(fondoNormal);
            	btnSalir.setForeground(negro);
            }
        });
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.BOTH;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 2;
		gbc_btnSalir.gridy = 8;
		contentPane.add(btnSalir, gbc_btnSalir);
	}
}