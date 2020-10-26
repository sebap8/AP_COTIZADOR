package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class LiberarDescuento extends JFrame {
	private JLabel lblOperario;
	private JButton jButtonEntrar;
	private JButton jButtonSalir;
	private JPasswordField txtContrasenia;
	private JLabel lblContrasenia;
	private JComboBox jComboBox1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LiberarDescuento inst = new LiberarDescuento();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	public LiberarDescuento() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				lblOperario = new JLabel();
				getContentPane().add(lblOperario);
				lblOperario.setText("USUARIO ");
				lblOperario.setBounds(12, 31, 91, 16);
			}
			{
				ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(
								new String[] {  });
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(109, 28, 158, 23);
				Vector<String> a=Controlador.getInstancia().obtenerUsuarios();
				for(int i=0;i<a.size();i++){
					jComboBox1.addItem(a.get(i));
				}
			}
			{
				lblContrasenia = new JLabel();
				getContentPane().add(lblContrasenia);
				lblContrasenia.setText("CONTRASEÑA");
				lblContrasenia.setBounds(12, 80, 91, 16);
			}
			{
				txtContrasenia = new JPasswordField();
				getContentPane().add(txtContrasenia);
				txtContrasenia.setBounds(109, 77, 158, 23);
			}
			{
				jButtonEntrar = new JButton();
				getContentPane().add(jButtonEntrar);
				jButtonEntrar.setText("ENTRAR");
				jButtonEntrar.setBounds(46, 122, 97, 39);
				this.rootPane.setDefaultButton(jButtonEntrar);
				jButtonEntrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(Controlador.getInstancia().verificarPermisoDescuento(jComboBox1.getSelectedItem().toString(),txtContrasenia.getText())) {
							GenerarPresupuesto.getInstancia().actualizarDescuentoSupervisor();
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Contraseña inválida o usuario sin privilegios. Intente nuevamente.","ERROR", JOptionPane.ERROR_MESSAGE);
							txtContrasenia.setText("");
						}
					}
				});
			}
			{
				jButtonSalir = new JButton();
				getContentPane().add(jButtonSalir);
				jButtonSalir.setText("SALIR");
				jButtonSalir.setBounds(170, 122, 97, 39);
				jButtonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
						
					}
				});
			}
			setSize(309, 218);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
