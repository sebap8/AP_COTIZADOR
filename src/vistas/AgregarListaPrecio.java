package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.ProductoPrecio;
import controlador.Controlador;

public class AgregarListaPrecio extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textCodigo;
	private JLabel lblRentabilidad;
	private JComboBox comboBoxDescuento;
	private JLabel lblCodigo;
	private JTextField textDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarListaPrecio frame = new AgregarListaPrecio();
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
	public AgregarListaPrecio() {
		setTitle("LISTA DE PRECIOS - AGREGAR LISTA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 587, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(126, 5, 236, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textNombre.getText().isEmpty()) {
					if(!textDescripcion.getText().isEmpty()) {
						if(!textCodigo.getText().isEmpty()) {
							if(!comboBoxDescuento.getSelectedItem().toString().equals("-")) {
								int descuento=Integer.valueOf(comboBoxDescuento.getSelectedItem().toString().replace("%", ""));
								if(Controlador.getInstancia().agregarListaDePrecio(textNombre.getText(),textDescripcion.getText(),textCodigo.getText(),descuento)) {
									JOptionPane.showMessageDialog(null, "Se ha agregado la nueva lista.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
									textNombre.setText("");
									textDescripcion.setText("");
									textCodigo.setText("");
									comboBoxDescuento.setSelectedItem("-");
								}else {
									JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar un descuento.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un código.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar una descripción.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
					
				
				
			}
		});
		btnAceptar.setBounds(139, 181, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(292, 181, 137, 41);
		panel.add(btnCancelar);
		
		lblCodigo = new JLabel("CÓDIGO");
		lblCodigo.setBounds(10, 90, 143, 16);
		panel.add(lblCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(126, 85, 172, 26);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		lblRentabilidad = new JLabel("RENTABILIDAD");
		lblRentabilidad.setBounds(10, 130, 143, 16);
		panel.add(lblRentabilidad);
		
		comboBoxDescuento = new JComboBox();
		comboBoxDescuento.setBounds(126, 126, 109, 27);
		panel.add(comboBoxDescuento);
		comboBoxDescuento.addItem("-");
		
		JLabel lblDescripcion = new JLabel("DESCRIPCIÓN");
		lblDescripcion.setBounds(10, 50, 109, 16);
		panel.add(lblDescripcion);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(126, 45, 455, 26);
		panel.add(textDescripcion);
		textDescripcion.setColumns(10);
		for(int i=0;i<40;i++) {
			comboBoxDescuento.addItem(i +"%");
		}
	}
}
