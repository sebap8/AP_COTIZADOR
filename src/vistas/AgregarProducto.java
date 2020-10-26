package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textM3;
	private JLabel lblStockMinimo;
	private JTextField textStockMinimo;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarProducto frame = new AgregarProducto();
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
	public AgregarProducto() {
		setTitle("PRODUCTO - AGREGAR PRODUCTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 508, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(116, 5, 367, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float m3 = Float.valueOf(textM3.getText().replace(",", "."));
					int stockMinimo = Integer.valueOf(textStockMinimo.getText());
					if(!textNombre.getText().isEmpty()) {
							if(Controlador.getInstancia().agregarProducto(textNombre.getText(),m3,stockMinimo)) {
								JOptionPane.showMessageDialog(null, "El producto ha sido agregado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
								textNombre.setText("");
								textM3.setText("");
								textStockMinimo.setText("");
							}else{
								JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de producto.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Verifique la información ingresada. \n M3: solo números enteros o decimales. \n STOCK MÍNIMO: solo números enteros.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(103, 145, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(256, 145, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblM3 = new JLabel("M3");
		lblM3.setBounds(10, 50, 89, 16);
		panel.add(lblM3);
		
		textM3 = new JTextField();
		textM3.setBounds(116, 43, 115, 26);
		panel.add(textM3);
		textM3.setColumns(10);
		
		lblStockMinimo = new JLabel("STOCK MÍNIMO");
		lblStockMinimo.setBounds(10, 90, 143, 16);
		panel.add(lblStockMinimo);
		
		textStockMinimo = new JTextField();
		textStockMinimo.setBounds(116, 83, 115, 26);
		panel.add(textStockMinimo);
		textStockMinimo.setColumns(10);
	}
}
