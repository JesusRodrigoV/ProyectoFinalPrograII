package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Pagos extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int id_cliente;
	private static JTextField usuario;
	private JPanel contentPane;
	private JTextField monto;
	private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
    private Color rojo = new Color(255, 91, 91);
    private Color fondoNormal = Color.WHITE;
    private Color negro = Color.BLACK;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagos frame = new Pagos(id_cliente, usuario);
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
	public Pagos(int id_cliente, JTextField usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 259);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 40, 30, 30, 30, 30, 40, 20, 0};
		gbl_contentPane.rowHeights = new int[]{37, 13, 19, 21, 35, 25, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Pagos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el monto:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		monto = new JTextField();
		GridBagConstraints gbc_monto = new GridBagConstraints();
		gbc_monto.fill = GridBagConstraints.HORIZONTAL;
		gbc_monto.insets = new Insets(0, 0, 5, 5);
		gbc_monto.gridwidth = 4;
		gbc_monto.gridx = 3;
		gbc_monto.gridy = 2;
		contentPane.add(monto, gbc_monto);
		monto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Servicio");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.gridwidth = 2;
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		contentPane.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		String[] serv = {"Luz", "Agua", "Gas", "Internet"};
		JComboBox servicio = new JComboBox<>(serv);
		GridBagConstraints gbc_servicio = new GridBagConstraints();
		gbc_servicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_servicio.insets = new Insets(0, 0, 5, 5);
		gbc_servicio.gridwidth = 4;
		gbc_servicio.gridx = 3;
		gbc_servicio.gridy = 3;
		contentPane.add(servicio, gbc_servicio);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 5;
		btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorder(new MatteBorder(1, 0, 0, 0, verdeOscuro));
		btnAceptar.setBackground(fondoNormal);
		btnAceptar.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		entraMouse(btnAceptar);
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		saleMouse(btnAceptar);
         	}
         });
		contentPane.add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cajero caja = new Cajero(id_cliente, usuario);
				caja.setVisible(true);
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 5;
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorder(new MatteBorder(1, 0, 0, 0, rojo));
		btnCancelar.setBackground(fondoNormal);
		btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnCancelar.setOpaque(true);
            	btnCancelar.setBackground(rojo);
				btnCancelar.setForeground(fondoNormal);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnCancelar.setOpaque(false);
            	btnCancelar.setBackground(fondoNormal);
            	btnCancelar.setForeground(negro);
            }
         });
		contentPane.add(btnCancelar, gbc_btnCancelar);
	}
	
	private void entraMouse(JButton boton){
        boton.setOpaque(true);
        boton.setBackground(verdeBoton);
        boton.setFont(new Font("Tahoma", Font.BOLD, 10));
    }
    private void saleMouse(JButton boton){
        boton.setOpaque(false);
        boton.setBackground(fondoNormal);
        boton.setFont(new Font("Tahoma", Font.BOLD, 11));
    }
}
