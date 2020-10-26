package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import beans.ProductoBean;
import beans.UsuarioBean;
import controlador.Controlador;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textStock;
	private JPanel panel;
	private JLabel lblStock;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField textM3;
	private JLabel lblStockMinimo;
	private JTextField textStockMinimo;
	private JLabel lblProducto;
	private JButton btnBuscar;
	private JComboBox comboBoxProductos;
	private static ModificarProducto instancia;
	private JLabel lblNombre;
	private JTextField textNombre;
	
	public static ModificarProducto getInstancia(){
		if(instancia==null){
			instancia=new ModificarProducto();
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
					ModificarProducto frame = new ModificarProducto();
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
	public ModificarProducto() {
		setTitle("USUARIO - MODIFICAR PRODUCTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 611, 276);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblStock = new JLabel("STOCK");
		lblStock.setBounds(10, 95, 61, 16);
		panel.add(lblStock);
		
		textStock = new JTextField();
		textStock.setBounds(117, 90, 137, 26);
		panel.add(textStock);
		textStock.setColumns(10);
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxProductos.getSelectedItem().equals("Ninguno")){
					textM3.setText("");
					textStockMinimo.setText("");
					textStock.setText("");
					ProductoBean p=Controlador.getInstancia().obtenerProducto(comboBoxProductos.getSelectedItem().toString());
					textM3.setText(String.valueOf(p.getM3()));
					textNombre.setText(p.getNombre());
					textStock.setText(String.valueOf(p.getStock()));
					textStockMinimo.setText(String.valueOf(p.getStockMinimo()));
				}
			}
		});
		comboBoxProductos.setBounds(117, 6, 350, 27);
		panel.add(comboBoxProductos);
		obtenerProductosCombo();
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int stock = Integer.valueOf(textStock.getText());
					int stockMinimo = Integer.valueOf(textStockMinimo.getText());
					float m3 = Float.valueOf(textM3.getText().replace(",", "."));
					if(!textNombre.getText().isEmpty()) {
						if(Controlador.getInstancia().modificarProducto(textNombre.getText(),textStock.getText(),String.valueOf(m3),textStockMinimo.getText(),comboBoxProductos.getSelectedItem().toString())) {
							textM3.setText("");
							textNombre.setText("");
							textStock.setText("");
							textStockMinimo.setText("");
							JOptionPane.showMessageDialog(null, "El producto ha sido modificado.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							instancia=null;
						}else{
							JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de producto.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());
					JOptionPane.showMessageDialog(null, "Verifique la información ingresada. \n M3: solo números enteros o decimales. \n STOCK MÍNIMO: solo números enteros.\n STOCK: solo números enteros.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(140, 218, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				instancia=null;
			}
		});
		btnCancelar.setBounds(293, 218, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblM3 = new JLabel("M3");
		lblM3.setBounds(10, 135, 89, 16);
		panel.add(lblM3);
		
		textM3 = new JTextField();
		textM3.setBounds(117, 130, 137, 26);
		panel.add(textM3);
		textM3.setColumns(10);
		
		lblStockMinimo = new JLabel("STOCK MÍNIMO");
		lblStockMinimo.setBounds(10, 175, 117, 16);
		panel.add(lblStockMinimo);
		
		textStockMinimo = new JTextField();
		textStockMinimo.setBounds(117, 170, 137, 26);
		panel.add(textStockMinimo);
		textStockMinimo.setColumns(10);
		
		lblProducto = new JLabel("PRODUCTO");
		lblProducto.setBounds(10, 10, 99, 16);
		panel.add(lblProducto);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProducto v = new BuscarProducto("2");
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnBuscar.setBounds(479, 5, 117, 29);
		panel.add(btnBuscar);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 50, 89, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(117, 45, 350, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		
	}
	private void obtenerProductosCombo() {
		Vector<String> usuarios=Controlador.getInstancia().obtenerProductos();
		comboBoxProductos.addItem("Ninguno");
		for(int i=0;i<usuarios.size();i++) {
			comboBoxProductos.addItem(usuarios.get(i));
		}
	}

	public void setProducto(String nombre) {
		comboBoxProductos.setSelectedItem(nombre);
		ProductoBean p=Controlador.getInstancia().obtenerProducto(nombre);
		textM3.setText(String.valueOf(p.getM3()));
		textNombre.setText(p.getNombre());
		textStock.setText(String.valueOf(p.getStock()));
		textStockMinimo.setText(String.valueOf(p.getStockMinimo()));
	}
}
