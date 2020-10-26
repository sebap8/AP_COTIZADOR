package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Controlador;

public class AgregarImagenProductoFinal extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private String ruta="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarImagenProductoFinal frame = new AgregarImagenProductoFinal();
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
	public AgregarImagenProductoFinal() {
		setTitle("PRODUCTO - AGREGAR PRODUCTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 508, 194);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(116, 5, 278, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		textNombre.setEnabled(false);
		
		btnAceptar = new JButton("Seleccionar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
		        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
		        j.setFileFilter(fil);
		        int s = j.showOpenDialog(null);
		        if(s == JFileChooser.APPROVE_OPTION){
		            ruta = j.getSelectedFile().getAbsolutePath();
		            textNombre.setText(ruta);
		        }
				
			}
		});
		btnAceptar.setBounds(104, 132, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("GUARDAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					byte[] icono = new byte[(int) ruta.length()];
		            InputStream input = new FileInputStream(ruta);
		            input.read(icono);
		            if(Controlador.getInstancia().guardarImagenProducto(icono,"SIP45 - OSB 11,1 + EPS 45 + OSB 11,1")) {
		            	
		            }else {
		            	
		            }
					dispose();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());
				}
				
			}
		});
		btnCancelar.setBounds(257, 132, 137, 41);
		panel.add(btnCancelar);
	}
}
