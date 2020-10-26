package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.UsuarioBean;
import controlador.Controlador;

public class EliminarUsuario extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarUsuario frame = new EliminarUsuario();
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
	public EliminarUsuario() {
		setTitle("USUARIO - ELIMINAR USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 153);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 376, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(83, 6, 199, 27);
		panel.add(comboBox);
		Vector<String> usuarios=Controlador.getInstancia().obtenerUsuarios();
		comboBox.addItem("Ninguno");
		for(int i=0;i<usuarios.size();i++) {
			comboBox.addItem(usuarios.get(i));
		}
		
		btnAceptar = new JButton("ELIMINAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBox.getSelectedItem().toString().equals("Ninguno")) {
					int resp=JOptionPane.showConfirmDialog(null,"¿Está seguro que desea eliminar el usuario"+comboBox.getSelectedItem().toString().toUpperCase()+" ?");
				      if (JOptionPane.OK_OPTION == resp){
				    	  if(Controlador.getInstancia().eliminarUsuario(comboBox.getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
				      }
					
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(48, 60, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(201, 60, 137, 41);
		panel.add(btnCancelar);
		
		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(10, 10, 61, 16);
		panel.add(lblUsuario);
		
		
	}
}
