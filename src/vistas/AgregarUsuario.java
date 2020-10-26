package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textApellido;
	private JLabel lblEmail;
	private JTextField textMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarUsuario frame = new AgregarUsuario();
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
	public AgregarUsuario() {
		setTitle("USUARIO - AGREGAR USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 194);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(83, 5, 130, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textNombre.getText().isEmpty()) {
					if(!textApellido.getText().isEmpty()) {
						if(!textMail.getText().isEmpty()) {
							if(Controlador.getInstancia().agregarUsuario(textNombre.getText(),textApellido.getText(),textMail.getText())) {
								JOptionPane.showMessageDialog(null, "El usuario ha sido creado. Usuario: "+textNombre.getText().toUpperCase().charAt(0)+textApellido.getText()+". \n Para su primer ingreso utilice la contraseña 1234 y siga los pasos.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un email.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe completar el campo apellido.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de usuario.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(61, 133, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(214, 133, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(10, 50, 89, 16);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(83, 45, 130, 26);
		panel.add(textApellido);
		textApellido.setColumns(10);
		
		lblEmail = new JLabel("E-MAIL");
		lblEmail.setBounds(10, 90, 61, 16);
		panel.add(lblEmail);
		
		textMail = new JTextField();
		textMail.setBounds(83, 85, 349, 26);
		panel.add(textMail);
		textMail.setColumns(10);
	}
}
