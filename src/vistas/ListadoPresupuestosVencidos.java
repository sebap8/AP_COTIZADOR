package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.PresupuestoBean;
import controlador.Controlador;

public class ListadoPresupuestosVencidos extends JFrame {

	private JPanel contentPane;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JPanel panel;
	private List<PresupuestoBean> presupuestos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoPresupuestosVencidos frame = new ListadoPresupuestosVencidos();
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
	public ListadoPresupuestosVencidos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 883, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel= new JPanel();
		panel.setBounds(10, 282, 775, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		jScrollPane1 = new JScrollPane();
		panel.add(jScrollPane1);
		jScrollPane1.setBounds(6, 58, 861, 367);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Número","Vencimiento","Cliente","Forma de pago","Total"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		JButton btnVer = new JButton("VER");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTable1.getSelectedRow()>=0) {
					Controlador.getInstancia().verPresupuesto(presupuestos.get(jTable1.getSelectedRow()));
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un presupuesto. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVer.setBounds(322, 6, 117, 40);
		panel.add(btnVer);
		
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(modelocentrarPrexpansion);
        
        presupuestos=Controlador.getInstancia().obtenerPresupuestosVencidos();
        for(int i=0;i<presupuestos.size();i++) {
        	PresupuestoBean p=presupuestos.get(i);
        	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
        	int numeroPresupuesto=p.getNumero();
			Object nuevo[]= {String.format("%08d", numeroPresupuesto),p.getFechaVencimiento(),p.getCliente(),p.getFormaDePago(),formatearImporte(p.getTotal())};
			temp.addRow(nuevo);
        }
        
        
        modelocentrarPrexpansion.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getTableHeader().setDefaultRenderer(modelocentrarPrexpansion);
	}

	private Object formatearImporte(float precio) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(precio);
	}
}
