package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import beans.ProductoBean;
import beans.UsuarioBean;
import controlador.Controlador;

public class AgregarImagenProducto extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblProducto;
	private JButton btnBuscar;
	private JComboBox comboBoxProductos;
	private static AgregarImagenProducto instancia;
	private JPanel panelImagen;
	private JLabel lblImagen;
	private JTextField textImagen;
	private JButton btnSeleccionar;
	private String ruta="";
	private JLabel lblImagenMuestra;
	
	public static AgregarImagenProducto getInstancia(){
		if(instancia==null){
			instancia=new AgregarImagenProducto();
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
					AgregarImagenProducto frame = new AgregarImagenProducto();
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
	public AgregarImagenProducto() {
		setTitle("PRODUCTO - AGREGAR IMAGEN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 611, 446);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(117, 6, 350, 27);
		panel.add(comboBoxProductos);
		obtenerProductosCombo();
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					byte[] icono = new byte[(int) ruta.length()];
		            InputStream input = new FileInputStream(ruta);
		            input.read(icono);
		            if(!comboBoxProductos.getSelectedItem().equals("Ninguno")) {
		            	if(!ruta.equals("")) {
		            		if(Controlador.getInstancia().guardarImagen(ruta,comboBoxProductos.getSelectedItem().toString())) {
		            			comboBoxProductos.setSelectedItem("Ninguno");
		            			textImagen.setText("");
		            			lblImagenMuestra.setIcon(new ImageIcon());
				            	JOptionPane.showMessageDialog(null, "Se ha agregado la imagen del producto.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				            }else {
				            	JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
				            }
		            	}else {
		            		JOptionPane.showMessageDialog(null, "Debe seleccionar una imagen.","ERROR", JOptionPane.ERROR_MESSAGE);
		            	}
		            }else {
		            	JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.","ERROR", JOptionPane.ERROR_MESSAGE);
		            }
		           
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(138, 399, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				instancia=null;
			}
		});
		btnCancelar.setBounds(291, 399, 137, 41);
		panel.add(btnCancelar);
		
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
		
		panelImagen = new JPanel();
		panelImagen.setBounds(117, 87, 350, 300);
		panel.add(panelImagen);
		panelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		panelImagen.setLayout(null);
		
		lblImagenMuestra = new JLabel("");
		lblImagenMuestra.setBounds(6, 7, 338, 287);
		panelImagen.add(lblImagenMuestra);
		lblImagenMuestra.setHorizontalAlignment(SwingConstants.CENTER);

		
		lblImagen = new JLabel("IMAGEN");
		lblImagen.setBounds(10, 50, 99, 16);
		panel.add(lblImagen);
		
		textImagen = new JTextField();
		textImagen.setBounds(117, 45, 350, 26);
		panel.add(textImagen);
		textImagen.setColumns(10);
		textImagen.setEnabled(false);
		
		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
		        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
		        j.setFileFilter(fil);
		        int s = j.showOpenDialog(null);
		        if(s == JFileChooser.APPROVE_OPTION){
		            ruta = j.getSelectedFile().getAbsolutePath();
		            textImagen.setText(ruta);
		        }
		        Image img=new ImageIcon(ruta).getImage();
		        ImageIcon img2=new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
		        lblImagenMuestra.setIcon(img2);
				
			}
		});
		btnSeleccionar.setBounds(479, 45, 117, 29);
		panel.add(btnSeleccionar);
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
		
	}
}
