package Vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controlador.ControladorReporte;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Reporte extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int id;
    private int id_cuenta;
    private JTable table;
    private DefaultTableModel tableModel;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
    private Color rojo = new Color(255, 91, 91);
    private Color fondoNormal = Color.WHITE;
    private Color negro = Color.BLACK;
    private Color blanco = Color.white;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reporte frame = new Reporte(0, 0);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Reporte(int id, int id_cuenta) {
        this.id = id;
        this.id_cuenta = id_cuenta;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 323);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 150, 100, 150, 20, 0};
        gbl_contentPane.rowHeights = new int[]{20, 15, 214, 25, 20};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("Reporte de cuenta");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 3;
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        ControladorReporte control = new ControladorReporte(id, id_cuenta);

        String[] columnNames = {"Fecha", "Tipo", "Monto"};
        Object[][] data = control.getReportData(); 
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        JTableHeader header = table.getTableHeader();
        header.setBackground(verdeOscuro);
        header.setForeground(blanco);
        header.setFont(header.getFont().deriveFont(12f));
        
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        contentPane.add(scrollPane, gbc_scrollPane);

        JLabel lblSaldo = new JLabel("Saldo: ");
        GridBagConstraints gbc_lblSaldo = new GridBagConstraints();
        gbc_lblSaldo.insets = new Insets(0, 0, 5, 5);
        gbc_lblSaldo.gridx = 1;
        gbc_lblSaldo.gridy = 3;
        contentPane.add(lblSaldo, gbc_lblSaldo);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(0, 0, 0, 0, verdeOscuro));
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
        
        JTextArea textAreaSaldo = new JTextArea(control.saldo());
        
        GridBagConstraints gbc_textAreaSaldo = new GridBagConstraints();
        gbc_textAreaSaldo.insets = new Insets(0, 0, 5, 5);
        gbc_textAreaSaldo.fill = GridBagConstraints.BOTH;
        gbc_textAreaSaldo.gridx = 3;
        gbc_textAreaSaldo.gridy = 3;
        contentPane.add(textAreaSaldo, gbc_textAreaSaldo);
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.fill = GridBagConstraints.BOTH;
        gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
        gbc_btnSalir.gridx = 2;
        gbc_btnSalir.gridy = 4; 
        contentPane.add(btnSalir, gbc_btnSalir);
    }

    public JTable getTable() {
        return table;
    }
}
