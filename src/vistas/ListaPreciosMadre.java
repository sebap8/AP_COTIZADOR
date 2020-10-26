package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import beans.ProductoPrecio;
import controlador.Controlador;

public class ListaPreciosMadre extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JPanel panelProductos;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private List<ProductoPrecio> precios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPreciosMadre frame = new ListaPreciosMadre();
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
	public ListaPreciosMadre() {
		setTitle("LISTA DE PRECIOS - CARGAR PRECIOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 879, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 867, 445);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<jTable1.getRowCount();i++){
					Float precio=Float.valueOf(jTable1.getValueAt(i, 1).toString().toString().replace(",", "."));
					precios.get(i).setPrecio(precio);
					
				}
				if(Controlador.getInstancia().actualizarListaDePrecios(precios)) {
					JOptionPane.showMessageDialog(null, "Se ha actualizado la lista con éxito.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
				}					
				
			}
		});
		btnAceptar.setBounds(250, 398, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(399, 398, 137, 41);
		panel.add(btnCancelar);
		
		panelProductos= new JPanel();
		panelProductos.setBounds(6, 6, 855, 380);
		panel.add(panelProductos);
		panelProductos.setLayout(null);
		panelProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		jScrollPane1 = new JScrollPane();
		panelProductos.add(jScrollPane1);
		jScrollPane1.setBounds(6, 6, 839, 368);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Producto","Precio"}){
			boolean[] canEdit = new boolean[]{
                    false, true
            };
			public boolean isCellEditable(int rowIndex, int columnIndex)
					    {
							return canEdit[columnIndex];
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		
		precios=Controlador.getInstancia().obtenerPreciosProductos();
		for(int i=0;i<precios.size();i++) {
			Object nuevo[]= {precios.get(i).getProducto(),precios.get(i).getPrecio()};
			DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
			temp.addRow(nuevo);
		}
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        
        
        modelocentrarPrexpansion.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getTableHeader().setDefaultRenderer(modelocentrarPrexpansion);
		
		
	}

}
