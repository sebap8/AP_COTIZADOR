package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class EliminarCliente extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JLabel lblCliente;
	private JButton btnBuscar;
	private JComboBox comboBoxClientes;
	private static EliminarCliente instancia;

	public static EliminarCliente getInstancia(){
		if(instancia==null){
			instancia=new EliminarCliente();
		}
		return instancia;
	}
	public void setInstancia() {
		instancia=null;
	}

	/**
	 * Create the frame.
	 */
	public EliminarCliente() {
		setTitle("CLIENTE - ELIMINAR CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 611, 129);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(117, 6, 350, 27);
		panel.add(comboBoxClientes);
		obtenerClientesCombo();
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxClientes.getSelectedItem().toString().equals("Ninguno")) {
					int resp=JOptionPane.showConfirmDialog(null,"¿Está seguro que desea eliminar el producto "+comboBoxClientes.getSelectedItem().toString()+" ?");
				      if (JOptionPane.OK_OPTION == resp){
				    	  if(Controlador.getInstancia().eliminarProducto(comboBoxClientes.getSelectedItem().toString())) {
				    		  dispose();
				    		  instancia=null;
				    		  JOptionPane.showMessageDialog(null, "El producto ha sido eliminado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
				      }
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			
				
			}
		});
		btnEliminar.setBounds(136, 68, 137, 41);
		panel.add(btnEliminar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				instancia=null;
			}
		});
		btnCancelar.setBounds(289, 68, 137, 41);
		panel.add(btnCancelar);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 10, 99, 16);
		panel.add(lblCliente);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCliente v = new BuscarCliente("1");
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnBuscar.setBounds(479, 5, 117, 29);
		panel.add(btnBuscar);
		
		
	}
	private void obtenerClientesCombo() {
		List<String> clientes=Controlador.getInstancia().obtenerClientes();
		comboBoxClientes.addItem("Ninguno");
		for(int i=0;i<clientes.size();i++) {
			comboBoxClientes.addItem(clientes.get(i));
		}
	}
	public void setCliente(String cliente) {
		comboBoxClientes.setSelectedItem(cliente);
	}
}
