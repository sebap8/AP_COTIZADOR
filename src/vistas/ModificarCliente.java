package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.ClienteBean;
import beans.ProductoBean;
import controlador.Controlador;

public class ModificarCliente extends JFrame {

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
	private JLabel lblCliente;
	private JComboBox comboBoxCliente;
	private static ModificarCliente instancia;
	
	public static ModificarCliente getInstancia(){
		if(instancia==null){
			instancia=new ModificarCliente();
		}
		return instancia;
	}
	public void setInstancia() {
		instancia=null;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarCliente frame = new ModificarCliente();
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
	public ModificarCliente() {
		setTitle("CLIENTE - MODIFICAR CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 587, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 45, 61, 16);
		panel.add(lblCuit);
		
		textCuit = new JTextField();
		textCuit.setBounds(122, 40, 172, 26);
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
											if(Controlador.getInstancia().modificarCliente(textCuit.getText(),textNombre.getText(), textDomicilio.getText(), comboBoxProvincia.getSelectedItem().toString(),textTelefono.getText(),textContacto.getText(),textTelContacto.getText(), textEmail.getText(),comboBoxCondicionIva.getSelectedItem().toString(),comboBoxCliente.getSelectedItem().toString())) {
												textContacto.setText("");
												textCuit.setText("");
												textDomicilio.setText("");
												textEmail.setText("");
												textNombre.setText("");
												textTelContacto.setText("");
												textTelefono.setText("");
												comboBoxCondicionIva.setSelectedItem("Ninguno");
												comboBoxProvincia.setSelectedItem("Ninguno");
												dispose();
												instancia=null;
												JOptionPane.showMessageDialog(null, "El cliente ha sido modificado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
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
		btnAceptar.setBounds(96, 433, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				instancia=null;
			}
		});
		btnCancelar.setBounds(249, 433, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("RAZON SOCIAL");
		lblNombre.setBounds(10, 83, 143, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(122, 78, 386, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		lblDomicilio = new JLabel("DOMICILIO");
		lblDomicilio.setBounds(10, 125, 143, 16);
		panel.add(lblDomicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setBounds(122, 120, 274, 26);
		panel.add(textDomicilio);
		textDomicilio.setColumns(10);
		
		lblProvincia = new JLabel("PROVINCIA");
		lblProvincia.setBounds(10, 165, 89, 16);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(122, 161, 274, 27);
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
		lblTelefono.setBounds(10, 205, 94, 16);
		panel.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(122, 200, 130, 26);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		lblContacto = new JLabel("CONTACTO");
		lblContacto.setBounds(10, 243, 94, 16);
		panel.add(lblContacto);
		
		textContacto = new JTextField();
		textContacto.setBounds(122, 238, 195, 26);
		panel.add(textContacto);
		textContacto.setColumns(10);
		
		lblTelContacto = new JLabel("TEL CONTACTO");
		lblTelContacto.setBounds(10, 285, 143, 16);
		panel.add(lblTelContacto);
		
		textTelContacto = new JTextField();
		textTelContacto.setBounds(122, 280, 130, 26);
		panel.add(textTelContacto);
		textTelContacto.setColumns(10);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(10, 325, 61, 16);
		panel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(122, 320, 318, 26);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		lblCondicionIva = new JLabel("CONDICION IVA");
		lblCondicionIva.setBounds(10, 365, 122, 16);
		panel.add(lblCondicionIva);
		
		comboBoxCondicionIva = new JComboBox();
		comboBoxCondicionIva.setBounds(122, 361, 264, 27);
		panel.add(comboBoxCondicionIva);
		comboBoxCondicionIva.addItem("Ninguno");
		comboBoxCondicionIva.addItem("RESPONSABLE INSCRIPTO");
		comboBoxCondicionIva.addItem("CONSUMIDOR FINAL");
		comboBoxCondicionIva.addItem("MONOTRIBUTO");
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 10, 122, 16);
		panel.add(lblCliente);
		
		comboBoxCliente = new JComboBox();
		comboBoxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCliente(comboBoxCliente.getSelectedItem().toString());
			}
		});
		comboBoxCliente.setBounds(122, 6, 318, 27);
		panel.add(comboBoxCliente);
		comboBoxCliente.addItem("Ninguno");
		List<String> clientes=Controlador.getInstancia().obtenerClientes();
		Collections.sort(clientes);
		for(int i=0;i<clientes.size();i++) {
			comboBoxCliente.addItem(clientes.get(i));
		}
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCliente b=new BuscarCliente("2");
				b.setLocationRelativeTo(null);
				b.setVisible(true);
			}
		});
		btnBuscar.setBounds(452, 5, 117, 29);
		panel.add(btnBuscar);
	}
	public void setCliente(String nombre) {
		comboBoxCliente.setSelectedItem(nombre);
		ClienteBean c=Controlador.getInstancia().obtenerCliente(nombre);
		textContacto.setText(c.getContacto());
		textCuit.setText(c.getCuit());
		textDomicilio.setText(c.getDomicilio());
		textEmail.setText(c.getEmail());
		textNombre.setText(c.getNombre());
		textTelContacto.setText(c.getTelContacto());
		textTelefono.setText(c.getTelefono());
		comboBoxCondicionIva.setSelectedItem(c.getCondicionIVA());
		comboBoxProvincia.setSelectedItem(c.getProvincia());
		
	}
}
