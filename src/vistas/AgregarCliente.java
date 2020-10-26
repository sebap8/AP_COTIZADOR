package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import javax.swing.JComboBox;

public class AgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textCuit;
	private JPanel panel;
	private JLabel lblCuit;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textNombre;
	private JLabel lblDomicilio;
	private JTextField textDomicilio;
	private JLabel lblProvincia;
	private JComboBox comboBoxProvincia;
	private JLabel lblTelefono;
	private JTextField textTelefono;
	private JLabel lblContacto;
	private JTextField textContacto;
	private JLabel lblTelContacto;
	private JTextField textTelContacto;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblCondicionIva;
	private JComboBox comboBoxCondicionIva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCliente frame = new AgregarCliente();
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
	public AgregarCliente() {
		setTitle("CLIENTE - AGREGAR CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 587, 445);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 10, 61, 16);
		panel.add(lblCuit);
		
		textCuit = new JTextField();
		textCuit.setBounds(126, 5, 172, 26);
		panel.add(textCuit);
		textCuit.setColumns(10);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textCuit.getText().isEmpty()) {
					if(!textNombre.getText().isEmpty()) {
						if(!textDomicilio.getText().isEmpty()) {
							if(!comboBoxProvincia.getSelectedItem().toString().equals("Ninguno")) {
								if(!textTelefono.getText().isEmpty()) {
									if(!textEmail.getText().isEmpty()) {
										if(!comboBoxCondicionIva.getSelectedItem().toString().equals("Ninguno")) {
											if(Controlador.getInstancia().agregarCliente(textCuit.getText(),textNombre.getText(), textDomicilio.getText(), comboBoxProvincia.getSelectedItem().toString(),textTelefono.getText(),textContacto.getText(),textTelContacto.getText(), textEmail.getText(),comboBoxCondicionIva.getSelectedItem().toString())) {
												textContacto.setText("");
												textCuit.setText("");
												textDomicilio.setText("");
												textEmail.setText("");
												textNombre.setText("");
												textTelContacto.setText("");
												textTelefono.setText("");
												comboBoxCondicionIva.setSelectedItem("Ninguno");
												comboBoxProvincia.setSelectedItem("Ninguno");
												JOptionPane.showMessageDialog(null, "El cliente se ha agregado al sistema.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
											}else {
												JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
											}
										}else {
											JOptionPane.showMessageDialog(null, "Debe seleccionar una condicion de IVA.","ERROR", JOptionPane.ERROR_MESSAGE);
										}
									}else {
										JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico.","ERROR", JOptionPane.ERROR_MESSAGE);
									}
									
								}else {
									JOptionPane.showMessageDialog(null, "Debe ingresar un teléfono.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar una provincia.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un domicilio.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar una razon social o nombre.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un cuit","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
					
				
				
			}
		});
		btnAceptar.setBounds(100, 398, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(253, 398, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("RAZON SOCIAL");
		lblNombre.setBounds(10, 50, 143, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(126, 43, 386, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		lblDomicilio = new JLabel("DOMICILIO");
		lblDomicilio.setBounds(10, 90, 143, 16);
		panel.add(lblDomicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setBounds(126, 85, 274, 26);
		panel.add(textDomicilio);
		textDomicilio.setColumns(10);
		
		lblProvincia = new JLabel("PROVINCIA");
		lblProvincia.setBounds(10, 130, 89, 16);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(126, 126, 274, 27);
		panel.add(comboBoxProvincia);
		comboBoxProvincia.addItem("Ninguno");
		comboBoxProvincia.addItem("BUENOS AIRES");
		comboBoxProvincia.addItem("CATAMARCA");
		comboBoxProvincia.addItem("CHACO");
		comboBoxProvincia.addItem("CHUBUT");
		comboBoxProvincia.addItem("CORDOBA");
		comboBoxProvincia.addItem("CORRIENTES");
		comboBoxProvincia.addItem("ENTRE RIOS");
		comboBoxProvincia.addItem("FORMOSA");
		comboBoxProvincia.addItem("JUJUY");
		comboBoxProvincia.addItem("LA PAMPA");
		comboBoxProvincia.addItem("LA RIOJA");
		comboBoxProvincia.addItem("MENDOZA");
		comboBoxProvincia.addItem("MISIONES");
		comboBoxProvincia.addItem("NEUQUEN");
		comboBoxProvincia.addItem("RIO NEGRO");
		comboBoxProvincia.addItem("SALTA");
		comboBoxProvincia.addItem("SAN JUAN");
		comboBoxProvincia.addItem("SAN LUIS");
		comboBoxProvincia.addItem("SANTA CRUZ");
		comboBoxProvincia.addItem("SANTA FE");
		comboBoxProvincia.addItem("SANTIAGO DEL ESTERO");
		comboBoxProvincia.addItem("TIERRA DEL FUEGO");
		comboBoxProvincia.addItem("TUCUMAN");
		
		
		lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setBounds(10, 170, 94, 16);
		panel.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(126, 165, 130, 26);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		lblContacto = new JLabel("CONTACTO");
		lblContacto.setBounds(10, 210, 94, 16);
		panel.add(lblContacto);
		
		textContacto = new JTextField();
		textContacto.setBounds(126, 203, 195, 26);
		panel.add(textContacto);
		textContacto.setColumns(10);
		
		lblTelContacto = new JLabel("TEL CONTACTO");
		lblTelContacto.setBounds(10, 250, 143, 16);
		panel.add(lblTelContacto);
		
		textTelContacto = new JTextField();
		textTelContacto.setBounds(126, 245, 130, 26);
		panel.add(textTelContacto);
		textTelContacto.setColumns(10);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(10, 290, 61, 16);
		panel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(126, 285, 318, 26);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		lblCondicionIva = new JLabel("CONDICION IVA");
		lblCondicionIva.setBounds(10, 330, 122, 16);
		panel.add(lblCondicionIva);
		
		comboBoxCondicionIva = new JComboBox();
		comboBoxCondicionIva.setBounds(126, 326, 264, 27);
		panel.add(comboBoxCondicionIva);
		comboBoxCondicionIva.addItem("Ninguno");
		comboBoxCondicionIva.addItem("RESPONSABLE INSCRIPTO");
		comboBoxCondicionIva.addItem("CONSUMIDOR FINAL");
		comboBoxCondicionIva.addItem("MONOTRIBUTO");
	}

}
