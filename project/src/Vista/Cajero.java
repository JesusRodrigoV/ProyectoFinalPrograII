// LIsto

package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorCajero;

import java.awt.event.*;
import java.awt.*;

public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField usuario;
    private static int id;
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
                    //Cajero frame = new Cajero();
                    Cajero frame = new Cajero(id, usuario);
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
    public Cajero(int id, JTextField usuario) {
    //public Cajero() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 125, 125, 125, 125, 20, 0};
        gbl_contentPane.rowHeights = new int[]{10, 30, 35, 30, 23, 33, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        ControladorCajero control = new ControladorCajero(id_cuenta, null, usuario);
        String nombre = control.nombre();

        System.out.println(nombre);

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setForeground(new Color(13, 171, 0));
        lblNombre.setBackground(new Color(13, 171, 0));
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.fill = GridBagConstraints.BOTH;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 3;
        gbc_lblNombre.gridy = 2;
        contentPane.add(lblNombre, gbc_lblNombre);
        
        JLabel lblBienvenido = new JLabel("Bienvenido ");
        lblBienvenido.setForeground(verdeOscuro);
        lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblBienvenido = new GridBagConstraints();
        gbc_lblBienvenido.anchor = GridBagConstraints.WEST;
        gbc_lblBienvenido.gridheight = 3;
        gbc_lblBienvenido.fill = GridBagConstraints.VERTICAL;
        gbc_lblBienvenido.insets = new Insets(0, 0, 5, 5);
        gbc_lblBienvenido.gridx = 2;
        gbc_lblBienvenido.gridy = 1;
        contentPane.add(lblBienvenido, gbc_lblBienvenido);

        //Seccion Panel Imagen

        //JPanel panel = new JPanel();
        ImagePanel panel = new ImagePanel("/resources/logoMejor.png");
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridheight = 3;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        contentPane.add(panel, gbc_panel);

        // Boton Transferencia

        JButton btnTransefer = new JButton("Transferencias");
        btnTransefer.setBackground(new Color(240, 240, 240));
        btnTransefer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transeferencia transfer = new Transeferencia(id, usuario);
                transfer.setVisible(true);
                
            }
        });
        GridBagConstraints gbc_btnTransefer = new GridBagConstraints();
        gbc_btnTransefer.fill = GridBagConstraints.BOTH;
        gbc_btnTransefer.insets = new Insets(0, 0, 5, 5);
        gbc_btnTransefer.gridx = 1;
        gbc_btnTransefer.gridy = 5;
        btnTransefer.setHorizontalAlignment(SwingConstants.CENTER);
        btnTransefer.setFocusPainted(false);
        btnTransefer.setBorder(new MatteBorder(0, 0, 1, 0, verdeOscuro));
        btnTransefer.setBackground(fondoNormal);
        btnTransefer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                entraMouse(btnTransefer);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                saleMouse(btnTransefer);
            }
            });
        contentPane.add(btnTransefer, gbc_btnTransefer);
        
        //Boton Salir Cerrar Sesion
        
        JButton btnSalir = new JButton("Cerrar Sesion");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Menu menu = new Menu();
            	menu.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.anchor = GridBagConstraints.NORTHEAST;
        gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
        gbc_btnSalir.gridx = 4;
        gbc_btnSalir.gridy = 1;
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(0, 0, 0, 0, verdeOscuro));
        btnSalir.setBackground(fondoNormal);
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSalir.setOpaque(true);
                btnSalir.setForeground(rojo);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnSalir.setOpaque(false);
                btnSalir.setForeground(negro);
            }
            });
        contentPane.add(btnSalir, gbc_btnSalir);
                
        //Boton Pagos
        
        JButton btnPagos = new JButton("Pagos");
        btnPagos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ControladorCajero control = new ControladorCajero(id_cuenta, null, usuario);
                Pagos pagar = new Pagos(id, usuario);
                pagar.setVisible(true);
        	}
        });
        GridBagConstraints gbc_btnPagos = new GridBagConstraints();
        gbc_btnPagos.fill = GridBagConstraints.BOTH;
        gbc_btnPagos.insets = new Insets(0, 0, 5, 5);
        gbc_btnPagos.gridx = 2;
        gbc_btnPagos.gridy = 5;
        btnPagos.setHorizontalAlignment(SwingConstants.CENTER);
        btnPagos.setFocusPainted(false);
        btnPagos.setBorder(new MatteBorder(0, 0, 1, 0, verdeOscuro));
        btnPagos.setBackground(fondoNormal);
        btnPagos.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		entraMouse(btnPagos);
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		saleMouse(btnPagos);
         	}
         });
        contentPane.add(btnPagos, gbc_btnPagos);

        id_cuenta = control.idCuenta(id);

        //Boton Reporte
        
        JButton btnReporte = new JButton("Reportes");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Reporte reporte = new Reporte(id, id_cuenta);
                reporte.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnReporte = new GridBagConstraints();
        gbc_btnReporte.fill = GridBagConstraints.BOTH;
        gbc_btnReporte.insets = new Insets(0, 0, 5, 5);
        gbc_btnReporte.gridx = 3;
        gbc_btnReporte.gridy = 5;
        btnReporte.setHorizontalAlignment(SwingConstants.CENTER);
        btnReporte.setFocusPainted(false);
        btnReporte.setBorder(new MatteBorder(0, 0, 1, 0, verdeOscuro));
        btnReporte.setBackground(fondoNormal);
        btnReporte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                entraMouse(btnReporte);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                saleMouse(btnReporte);
            }
            });
        contentPane.add(btnReporte, gbc_btnReporte);

        //Boton Perfil
        
        JButton btnPerfil = new JButton("Perfil");
        btnPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Perfil perfil = new Perfil(id, id_cuenta);
        		perfil.setVisible(true);
        		
        	}
        });
        GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
        gbc_btnPerfil.fill = GridBagConstraints.BOTH;
        gbc_btnPerfil.insets = new Insets(0, 0, 5, 5);
        gbc_btnPerfil.gridx = 4;
        gbc_btnPerfil.gridy = 5;
        btnPerfil.setHorizontalAlignment(SwingConstants.CENTER);
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorder(new MatteBorder(0, 0, 1, 0, verdeOscuro));
        btnPerfil.setBackground(fondoNormal);
        btnPerfil.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		entraMouse(btnPerfil);
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		saleMouse(btnPerfil);
         	}
         });
        contentPane.add(btnPerfil, gbc_btnPerfil);
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


