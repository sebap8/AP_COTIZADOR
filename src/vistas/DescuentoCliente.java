package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class DescuentoCliente extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblDescuentoAutorizado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JComboBox comboBoxDescuentoAutorizado;
	private JLabel lblDescuentoMaximoSupervisor;
	private JComboBox comboBoxDescuentoSupervisor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescuentoCliente frame = new DescuentoCliente();
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
	public DescuentoCliente() {
		setTitle("PARAMETRIZACIÓN - DESCUENTO CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 442, 171);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblDescuentoAutorizado = new JLabel("DESCUENTO MAXIMO AUTORIZADO");
		lblDescuentoAutorizado.setBounds(10, 10, 249, 16);
		panel.add(lblDescuentoAutorizado);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String autorizado=comboBoxDescuentoAutorizado.getSelectedItem().toString();
				String noAutorizado = comboBoxDescuentoSupervisor.getSelectedItem().toString();
				if(!autorizado.equals("-")) {
					if(!noAutorizado.equals("-")) {
						int descAutorizado=Integer.valueOf(autorizado.replace(" %", ""));
						int descSupervisor =Integer.valueOf(noAutorizado.replace(" %", ""));
						if(descAutorizado<=descSupervisor) {
							if(Controlador.getInstancia().actualizarDescuentos(descAutorizado,descSupervisor)) {
								JOptionPane.showMessageDialog(null, "Se han actualizado los descuentos.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador. ","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "El descuento autorizado debe ser menor o igual que el descuento de supervisor ","ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un descuento de supervisor. ","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un descuento autorizado. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(87, 107, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(240, 107, 137, 41);
		panel.add(btnCancelar);
		
		comboBoxDescuentoAutorizado = new JComboBox();
		comboBoxDescuentoAutorizado.setBounds(271, 6, 87, 27);
		panel.add(comboBoxDescuentoAutorizado);
		comboBoxDescuentoAutorizado.addItem("-");
		for(int i=0;i<50;i++) {
			comboBoxDescuentoAutorizado.addItem(i +" %");
		}
		comboBoxDescuentoAutorizado.setSelectedItem(String.valueOf(Controlador.getInstancia().obtenerDescuentoAutorizado())+" %");
		
		lblDescuentoMaximoSupervisor = new JLabel("DESCUENTO MAXIMO SUPERVISOR");
		lblDescuentoMaximoSupervisor.setBounds(10, 50, 249, 16);
		panel.add(lblDescuentoMaximoSupervisor);
		
		comboBoxDescuentoSupervisor = new JComboBox();
		comboBoxDescuentoSupervisor.setBounds(271, 45, 87, 27);
		panel.add(comboBoxDescuentoSupervisor);
		comboBoxDescuentoSupervisor.addItem("-");
		for(int i=0;i<80;i++) {
			comboBoxDescuentoSupervisor.addItem(i +" %");
		}
		comboBoxDescuentoSupervisor.setSelectedItem(Controlador.getInstancia().obtenerDescuentoSupervisor()+" %");
	}

}
