package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Cuentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cuentas frame = new Cuentas();
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
	public Cuentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 90, 90, 90, 90, 30, 0};
		gbl_contentPane.rowHeights = new int[]{31, 100, 100, 30, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton clasica = new JButton("Ahorro Clásico");
		GridBagConstraints gbc_clasica = new GridBagConstraints();
		gbc_clasica.insets = new Insets(0, 0, 5, 5);
		gbc_clasica.gridx = 1;
		gbc_clasica.gridy = 2;
		contentPane.add(clasica, gbc_clasica);
		
		JButton junior = new JButton("Ahorro Junior");
		GridBagConstraints gbc_junior = new GridBagConstraints();
		gbc_junior.insets = new Insets(0, 0, 5, 5);
		gbc_junior.gridx = 2;
		gbc_junior.gridy = 2;
		contentPane.add(junior, gbc_junior);
		
		JButton Senior = new JButton("Ahorro Senior\r\n");
		GridBagConstraints gbc_Senior = new GridBagConstraints();
		gbc_Senior.insets = new Insets(0, 0, 5, 5);
		gbc_Senior.gridx = 3;
		gbc_Senior.gridy = 2;
		contentPane.add(Senior, gbc_Senior);
		
		JButton mayor = new JButton("Ahorro Mayor");
		GridBagConstraints gbc_mayor = new GridBagConstraints();
		gbc_mayor.insets = new Insets(0, 0, 5, 5);
		gbc_mayor.gridx = 4;
		gbc_mayor.gridy = 2;
		contentPane.add(mayor, gbc_mayor);
	}

}
