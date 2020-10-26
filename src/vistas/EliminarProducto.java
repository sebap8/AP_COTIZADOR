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

import beans.ProductoBean;
import controlador.Controlador;

public class EliminarProducto extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JLabel lblProducto;
	private JButton btnBuscar;
	private JComboBox comboBoxProductos;
	private static EliminarProducto instancia;

	public static EliminarProducto getInstancia(){
		if(instancia==null){
			instancia=new EliminarProducto();
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
					EliminarProducto frame = new EliminarProducto();
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
	public EliminarProducto() {
		setTitle("PRODUCTO - ELIMINAR PRODUCTO");
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
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(117, 6, 350, 27);
		panel.add(comboBoxProductos);
		obtenerProductosCombo();
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxProductos.getSelectedItem().toString().equals("Ninguno")) {
					int resp=JOptionPane.showConfirmDialog(null,"¿Está seguro que desea eliminar el producto "+comboBoxProductos.getSelectedItem().toString()+" ?");
				      if (JOptionPane.OK_OPTION == resp){
				    	  if(Controlador.getInstancia().eliminarProducto(comboBoxProductos.getSelectedItem().toString())) {
				    		  dispose();
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
		
		lblProducto = new JLabel("PRODUCTO");
		lblProducto.setBounds(10, 10, 99, 16);
		panel.add(lblProducto);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProducto v = new BuscarProducto("1");
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnBuscar.setBounds(479, 5, 117, 29);
		panel.add(btnBuscar);
		
		
	}
	private void obtenerProductosCombo() {
		Vector<String> usuarios=Controlador.getInstancia().obtenerProductos();
		comboBoxProductos.addItem("Ninguno");
		for(int i=0;i<usuarios.size();i++) {
			comboBoxProductos.addItem(usuarios.get(i));
		}
	}
	public void setProducto(String producto) {
		comboBoxProductos.setSelectedItem(producto);
		
	}
}
