package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParametrizarUsuario extends JFrame {

	private JPanel contentPane;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	
	private JScrollPane jScrollPane2;
	private TableModel jTable1Model2;
	private JTable jTable2;
	private JButton buttonSacarTodos;
	private JButton buttonSacarIndividual;
	private Vector<String> otorgados=new Vector<String>();
	private Vector<String> disponibles=new Vector<String>();
	private JButton buttonAgregarTodos;
	private JButton buttonAgregarIndividual;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParametrizarUsuario frame = new ParametrizarUsuario();
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
	public ParametrizarUsuario() {
		setTitle("USUARIO - PARAMETRIZAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 600, 355);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JLabel lblUsaurio = new JLabel("USAURIO");
		lblUsaurio.setBounds(10, 29, 65, 14);
		panel.add(lblUsaurio);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox.getSelectedItem().equals("Ninguno")){
					//LIMPIO TABLA
					DefaultTableModel temp1 = (DefaultTableModel) jTable2.getModel();
					while(jTable2.getRowCount()>0){
						temp1.removeRow(jTable2.getRowCount()-1);
					}//FIN LIMPIO TABLA
					//CARGO PERMISOS OTORGADOS
					otorgados=Controlador.getInstancia().obtenerPermisosOtrogados(comboBox.getSelectedItem().toString());
					for(int i=0;i<otorgados.size();i++){
						Object nuevo[]= {otorgados.get(i)};
				    	DefaultTableModel temp = (DefaultTableModel) jTable2.getModel();			
				    	temp.addRow(nuevo);
					}//FIN CARGO PERMISOS OTORGADOS
					
					//LIMPIO TABLA
					DefaultTableModel temp2 = (DefaultTableModel) jTable1.getModel();
					while(jTable1.getRowCount()>0){
						temp2.removeRow(jTable1.getRowCount()-1);
					}//FIN LIMPIO TABLA
					//CARGO PERMISOS OTORGADOS
					disponibles=Controlador.getInstancia().obtenerPermisosDisponibles(comboBox.getSelectedItem().toString());
					for(int i=0;i<disponibles.size();i++){
						Object nuevo[]= {disponibles.get(i)};
				    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
				    	temp.addRow(nuevo);
					}//FIN CARGO PERMISOS OTORGADOS
					
					
				}
			}
		});
		comboBox.setBounds(97, 26, 166, 20);
		panel.add(comboBox);
		
		comboBox.addItem("Ninguno");
		Vector<String> a=Controlador.getInstancia().obtenerUsuariosCotizadorPanel();
		Collections.sort(a);
		for(int i=0;i<a.size();i++){
			comboBox.addItem(a.get(i));
		}
		
		jScrollPane1 = new JScrollPane();
		panel.add(jScrollPane1);
		jScrollPane1.setBounds(22, 80, 200, 157);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Permisos disponibles"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		
		jScrollPane2 = new JScrollPane();
		panel.add(jScrollPane2);
		jScrollPane2.setBounds(374, 80, 200, 157);
		jTable1Model2 = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Permisos otorgados"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable2 = new JTable();
		jScrollPane2.setViewportView(jTable2);
		jTable2.setModel(jTable1Model2);
		jTable2.setBounds(12, 207, 2, 194);
		
		buttonAgregarTodos = new JButton(">>");
		buttonAgregarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<jTable1.getRowCount();i++){
					
					Object nuevo[]= {jTable1.getValueAt(i, 0).toString()};
			    	DefaultTableModel temp = (DefaultTableModel) jTable2.getModel();			
			    	temp.addRow(nuevo);
			    	
				}
				while(jTable1.getRowCount()>0){
					DefaultTableModel temp2 = (DefaultTableModel) jTable1.getModel();			
			    	temp2.removeRow(jTable1.getRowCount()-1);
				}
			}
		});
		buttonAgregarTodos.setBounds(274, 99, 49, 23);
		panel.add(buttonAgregarTodos);
		
		buttonAgregarIndividual = new JButton(">");
		buttonAgregarIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTable1.getSelectedRow()>=0){
					Object nuevo[]= {jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()};
			    	DefaultTableModel temp = (DefaultTableModel) jTable2.getModel();			
			    	temp.addRow(nuevo);
			    	
			    	temp = (DefaultTableModel) jTable1.getModel();
			    	temp.removeRow(jTable1.getSelectedRow());
				}
			}
		});
		buttonAgregarIndividual.setBounds(274, 133, 49, 23);
		panel.add(buttonAgregarIndividual);
		
		buttonSacarTodos = new JButton("<<");
		buttonSacarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0;i<jTable2.getRowCount();i++){
					Object nuevo[]= {jTable2.getValueAt(i, 0).toString()};
			    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
			    	temp.addRow(nuevo);
				}
				while(jTable2.getRowCount()>0){
					DefaultTableModel temp2 = (DefaultTableModel) jTable2.getModel();			
			    	temp2.removeRow(jTable2.getRowCount()-1);
				}
			}
		});
		buttonSacarTodos.setBounds(274, 201, 49, 23);
		panel.add(buttonSacarTodos);
		
		buttonSacarIndividual = new JButton("<");
		buttonSacarIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTable2.getSelectedRow()>=0){
					Object nuevo[]= {jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()};
			    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
			    	temp.addRow(nuevo);
			    	
			    	temp = (DefaultTableModel) jTable2.getModel();
			    	temp.removeRow(jTable2.getSelectedRow());
				}
			}
		});
		buttonSacarIndividual.setBounds(274, 167, 49, 23);
		panel.add(buttonSacarIndividual);
		
		JButton btnAceptare = new JButton("ACEPTAR");
		btnAceptare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> permisos=new Vector<String>();
				for(int i=0;i<jTable2.getRowCount();i++){
					permisos.add(jTable2.getValueAt(i, 0).toString());
				}
				if(Controlador.getInstancia().cambiarPermisos(permisos,comboBox.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(null, "Se han otorgado los permisos al usuario.","INFORMACION", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "No se han podido otorgar los permisos. Comun�quese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptare.setBounds(322, 291, 118, 40);
		panel.add(btnAceptare);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(151, 291, 118, 40);
		panel.add(btnCancelar);
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		jTable2.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		
		
		
	}
}
