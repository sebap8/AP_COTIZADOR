package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.UsuarioBean;
import controlador.Controlador;
import javax.swing.JComboBox;

public class ModificarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textApellido;
	private JLabel lblEmail;
	private JTextField textMail;
	private JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
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
	public ModificarUsuario() {
		setTitle("USUARIO - MODIFICAR USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 233);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 50, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(83, 45, 147, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBox.getSelectedItem().equals("Ninguno")){
					textApellido.setText("");
					textMail.setText("");
					textNombre.setText("");
					UsuarioBean u=Controlador.getInstancia().obtenerUsuario(comboBox.getSelectedItem().toString());
					textApellido.setText(u.getApellido());
					textNombre.setText(u.getNombre());
					textMail.setText(u.getEmail());
				}
			}
		});
		comboBox.setBounds(83, 6, 143, 27);
		panel.add(comboBox);
		Vector<String> usuarios=Controlador.getInstancia().obtenerUsuarios();
		comboBox.addItem("Ninguno");
		for(int i=0;i<usuarios.size();i++) {
			comboBox.addItem(usuarios.get(i));
		}
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textNombre.getText().isEmpty()) {
					if(!textApellido.getText().isEmpty()) {
						if(!textMail.getText().isEmpty()) {
							if(Controlador.getInstancia().modificarUsuario(textNombre.getText(),textApellido.getText(),textMail.getText(),comboBox.getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(null, "El usuario ha sido modificado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
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
		btnAceptar.setBounds(57, 176, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(210, 176, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(10, 90, 89, 16);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(83, 85, 147, 26);
		panel.add(textApellido);
		textApellido.setColumns(10);
		
		lblEmail = new JLabel("E-MAIL");
		lblEmail.setBounds(10, 130, 61, 16);
		panel.add(lblEmail);
		
		textMail = new JTextField();
		textMail.setBounds(83, 125, 349, 26);
		panel.add(textMail);
		textMail.setColumns(10);
		
		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(10, 10, 61, 16);
		panel.add(lblUsuario);
		
		
	}
}
