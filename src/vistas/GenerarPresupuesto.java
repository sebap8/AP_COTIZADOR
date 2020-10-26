package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import beans.ItemPresupuestoBean;
import beans.ListaPrecio;
import beans.PresupuestoBean;
import controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class GenerarPresupuesto extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblPresupuestoN;
	private JLabel lblNumeroPresupuesto;
	private JLabel lblCliente;
	private JComboBox comboBoxClientes;
	private JComboBox comboBoxListaPrecios;
	private JComboBox comboBoxProductos;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JLabel lblFecha;
	private JLabel lblFecha2;
	private JLabel lblValidez;
	private JTextField textValidez;
	private JPanel panelProductos;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JButton btnBuscarCliente;
	private JButton btnBuscarListaPrecio;
	private JButton btnBuscarProducto;
	private JLabel lblSubtotal;
	private JLabel lblIva;
	private JLabel lblTotal;
	private JButton btnGuardar;
	private JButton btnCerrar;
	private JLabel lblSubtotal2;
	private JLabel lblIva2;
	private JLabel lblTotal2;
	private JComboBox comboBoxValidez ;
	private static GenerarPresupuesto instancia;
	private JLabel lblDescuento;
	private JComboBox comboBoxDescuento;
	private JButton btnDescuentoSupervisor;
	private JLabel lblFormaDePago;
	private JComboBox comboBoxFormaDePago;
	
	public static GenerarPresupuesto getInstancia(){
		if(instancia==null){
			instancia=new GenerarPresupuesto();
		}
		return instancia;
	}
	public static void setInstancia() {
		instancia=null;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarPresupuesto frame = new GenerarPresupuesto();
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
	public GenerarPresupuesto() {
		setTitle("PRESUPUESTOS - NUEVO PRESUPUESTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel = new JPanel();
		panel.setBounds(6, 6, 587, 445);
		contentPane.add(panel);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		panel.setLayout(null);
		
		 lblPresupuestoN = new JLabel("PRESUPUESTO Nº");
		lblPresupuestoN.setBounds(10, 50, 144, 16);
		panel.add(lblPresupuestoN);
		
		lblNumeroPresupuesto = new JLabel(Controlador.getInstancia().obtenerNumeroPresupuesto());
		lblNumeroPresupuesto.setBounds(166, 50, 92, 16);
		panel.add(lblNumeroPresupuesto);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 134, 101, 16);
		panel.add(lblCliente);
		
		comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(166, 130, 471, 27);
		panel.add(comboBoxClientes);
		List<String> clientes = Controlador.getInstancia().obtenerClientes();
		Collections.sort(clientes);
		comboBoxClientes.addItem("Ninguno");
		for(int i=0;i<clientes.size();i++) {
			comboBoxClientes.addItem(clientes.get(i));
		}
		
		JLabel lblListaDePrecios = new JLabel("LISTA DE PRECIOS");
		lblListaDePrecios.setBounds(10, 174, 148, 16);
		panel.add(lblListaDePrecios);
		
		comboBoxListaPrecios = new JComboBox();
		comboBoxListaPrecios.setBounds(166, 170, 295, 27);
		panel.add(comboBoxListaPrecios);
		List<ListaPrecio> listaPrecios=Controlador.getInstancia().obtenerListasDePrecios();
		comboBoxListaPrecios.addItem("Ninguno");
		for(int i=0;i<listaPrecios.size();i++) {
			comboBoxListaPrecios.addItem(listaPrecios.get(i).getNombre());
		}
		
		JLabel lblProducto = new JLabel("PRODUCTO");
		lblProducto.setBounds(10, 214, 110, 16);
		panel.add(lblProducto);
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(166, 210, 471, 27);
		panel.add(comboBoxProductos);
		List<String> productos=Controlador.getInstancia().obtenerProductos();
		Collections.sort(productos);
		comboBoxProductos.addItem("Ninguno");
		for(int i=0;i<productos.size();i++) {
			comboBoxProductos.addItem(productos.get(i));
		}
		
		lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(10, 254, 97, 16);
		panel.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(166, 249, 110, 26);
		panel.add(textCantidad);
		textCantidad.setColumns(10);
		
		lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 10, 61, 16);
		panel.add(lblFecha);
		
		lblFecha2 = new JLabel(generarFecha());
		lblFecha2.setBounds(166, 10, 126, 16);
		panel.add(lblFecha2);
		
		lblValidez = new JLabel("VALIDEZ (DÍAS)");
		lblValidez.setBounds(10, 90, 126, 16);
		panel.add(lblValidez);
		

		
		panelProductos= new JPanel();
		panelProductos.setBounds(10, 282, 775, 183);
		panel.add(panelProductos);
		panelProductos.setLayout(null);
		panelProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		jScrollPane1 = new JScrollPane();
		panelProductos.add(jScrollPane1);
		jScrollPane1.setBounds(6, 6, 763, 171);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Producto","Cantidad","Precio","Total"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantidad=Integer.valueOf(textCantidad.getText());
					if(!comboBoxListaPrecios.getSelectedItem().toString().equals("Ninguno")) {
						comboBoxListaPrecios.setEnabled(false);
						btnBuscarListaPrecio.setEnabled(false);
						if(!comboBoxProductos.getSelectedItem().toString().equals("Ninguno")) {
							if(!existeProductoEnTabla()) {
								String producto=comboBoxProductos.getSelectedItem().toString();
								int indexLista=comboBoxListaPrecios.getSelectedIndex()-1; //LE AGREGO -1 porque le agregué a el combo la frase NINGUNO
								int ganancia = listaPrecios.get(indexLista).getDescuento();
								float porcentajeGanancia=Float.valueOf((100+ganancia))/100;
								float precio=Controlador.getInstancia().obtenerPrecioProducto(producto)*porcentajeGanancia;
								float total=precio*cantidad;
								
								if(precio>0) {
									float subtotal=Float.valueOf(lblSubtotal2.getText().replace(",", "."));
									subtotal+=total;
									lblSubtotal2.setText(formatearImporte(subtotal));
									
									float iva=Float.valueOf(lblIva2.getText().replace(",", "."));
									iva+=(total)*0.21;
									lblIva2.setText(formatearImporte(iva));
									
									float totalFinal=Float.valueOf(lblTotal2.getText().replace(",", "."));
									totalFinal+=subtotal+iva;
									lblTotal2.setText(formatearImporte(totalFinal));
									
									DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
									Object nuevo[]= {comboBoxProductos.getSelectedItem().toString(),cantidad,formatearImporte(precio),formatearImporte(total)};
									temp.addRow(nuevo);
									textCantidad.setText("");
								}else {
									JOptionPane.showMessageDialog(null, "Debe cargar un precio para el producto. ","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "El producto seleccionado ya se encuentra agregado. ","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else{
							JOptionPane.showMessageDialog(null, "Debe seleccionar un producto. ","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una lista de precios. ","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros en el campo cantidad. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				
			
			}

			private boolean existeProductoEnTabla() {
				boolean bandera=false;
				for(int i=0;i<jTable1.getRowCount() && !bandera;i++) {
					if(jTable1.getValueAt(i, 0).toString().equals(comboBoxProductos.getSelectedItem().toString())) {
						bandera=true;
					}
				}
				return bandera;
			}

		});
		btnAgregar.setBounds(307, 249, 117, 29);
		panel.add(btnAgregar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila=jTable1.getSelectedRow();
				if(fila>=0) {
					int indexLista=comboBoxListaPrecios.getSelectedIndex()-1;
					int ganancia = listaPrecios.get(indexLista).getDescuento();
					float porcentajeGanancia=Float.valueOf((100+ganancia))/100;
					
					int cantidad=Integer.valueOf(jTable1.getValueAt(fila, 1).toString());
					float precio=Float.valueOf(jTable1.getValueAt(fila, 2).toString().replace(",", "."));
					
					float total=precio*cantidad;

					float subtotal=Float.valueOf(lblSubtotal2.getText().replace(",", "."));
					subtotal-=total;
					lblSubtotal2.setText(formatearImporte(subtotal));
					
					float iva=Float.valueOf(lblIva2.getText().replace(",", "."));
					float ivaAux=(float) ((precio*Float.valueOf(cantidad))*0.21);
					iva-=ivaAux;
					lblIva2.setText(formatearImporte(iva));
					
					
					float totalFinal=Float.valueOf(lblTotal2.getText().replace(",", "."));
					totalFinal-=(total+ivaAux);
					lblTotal2.setText(formatearImporte(totalFinal));
					
					DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
					temp.removeRow(jTable1.getSelectedRow());
					if(jTable1.getRowCount()==0) {
						comboBoxListaPrecios.setEnabled(true);
						btnBuscarListaPrecio.setEnabled(true);
					}
				
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un item de la tabla. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(448, 249, 117, 29);
		panel.add(btnEliminar);
		
		btnBuscarCliente = new JButton("BUSCAR");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCliente b =new BuscarCliente("3");
				b.setLocationRelativeTo(null);
				b.setVisible(true);
				
			}
		});
		btnBuscarCliente.setBounds(649, 129, 117, 29);
		panel.add(btnBuscarCliente);
		
		btnBuscarListaPrecio = new JButton("BUSCAR");
		btnBuscarListaPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarListaPrecio v = new BuscarListaPrecio("3");
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnBuscarListaPrecio.setBounds(520, 169, 117, 29);
		panel.add(btnBuscarListaPrecio);
		
		btnBuscarProducto = new JButton("BUSCAR");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProducto b = new BuscarProducto("3");
				b.setLocationRelativeTo(null);
				b.setVisible(true);
			}
		});
		btnBuscarProducto.setBounds(649, 209, 117, 29);
		panel.add(btnBuscarProducto);
		
		lblSubtotal = new JLabel("SUBTOTAL");
		lblSubtotal.setBounds(10, 524, 101, 16);
		panel.add(lblSubtotal);
		
		lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 564, 101, 16);
		panel.add(lblIva);
		
		lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(6, 604, 105, 16);
		panel.add(lblTotal);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxFormaDePago.getSelectedItem().equals("Ninguno")) {
					if(!comboBoxClientes.getSelectedItem().equals("Ninguno")) {
						if(!comboBoxListaPrecios.getSelectedItem().equals("Ninguno")) {
							if(jTable1.getRowCount()>0) {
								PresupuestoBean p = new PresupuestoBean();
								p.setFecha(lblFecha2.getText());
								p.setNumero(Integer.valueOf(lblNumeroPresupuesto.getText()));
								p.setValidez(Integer.valueOf(comboBoxValidez.getSelectedItem().toString()));
								p.setCliente(comboBoxClientes.getSelectedItem().toString());
								p.setListaPrecio(comboBoxListaPrecios.getSelectedItem().toString());
								p.setSubtotal(Float.valueOf(lblSubtotal2.getText().replace(",", ".")));
								p.setIva(Float.valueOf(lblIva2.getText().replace(",", ".")));
								p.setTotal(Float.valueOf(lblTotal2.getText().replace(",", ".")));
								p.setFormaDePago(comboBoxFormaDePago.getSelectedItem().toString());
								p.setDescuento(Integer.valueOf(comboBoxDescuento.getSelectedItem().toString().replace(" %", "")));
								for(int i=0;i<jTable1.getRowCount();i++){
									ItemPresupuestoBean it=new ItemPresupuestoBean();
									it.setProducto(jTable1.getValueAt(i, 0).toString());
									it.setCantidad(Integer.valueOf(jTable1.getValueAt(i, 1).toString()));
									it.setPrecio(Float.valueOf(jTable1.getValueAt(i, 2).toString().replace(",", ".")));
									p.getItems().add(it);
								}
								if(Controlador.getInstancia().generarPresupuesto(p)) {
									Controlador.getInstancia().generarPDF2(p);
//									Controlador.getInstancia().enviarMailsPresupuestoNuevo(p);
//									Controlador.getInstancia().enviarPresupuestoPorMail(p);
									lblNumeroPresupuesto.setText(Controlador.getInstancia().obtenerNumeroPresupuesto());
									comboBoxClientes.setSelectedItem("Ninguno");
									comboBoxListaPrecios.setSelectedItem("Ninguno");
									comboBoxListaPrecios.setEnabled(true);
									btnBuscarListaPrecio.setEnabled(true);
									comboBoxProductos.setSelectedItem("Ninguno");
									lblSubtotal2.setText("0");
									lblIva2.setText("0");
									lblTotal2.setText("0");
									comboBoxDescuento.setSelectedItem("0 %");
									comboBoxFormaDePago.setSelectedItem("Ninguno");
									int filas=jTable1.getRowCount();
									for(int i=0;i<filas;i++) {
										DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
										temp.removeRow(0);
									}
									JOptionPane.showMessageDialog(null, "Se ha generado el presupuesto.","INFORMACION", JOptionPane.INFORMATION_MESSAGE);
									
								}else {
									JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Comuníquese con el administrador. ","ERROR", JOptionPane.ERROR_MESSAGE);
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "Debe agregar productos al presupuesto. ","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar una lista de precios. ","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente. ","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una forma de pago. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(234, 631, 137, 41);
		panel.add(btnGuardar);
		
		btnCerrar = new JButton("CERRAR");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				instancia=null;
			}
		});
		btnCerrar.setBounds(403, 631, 137, 41);
		panel.add(btnCerrar);
		
		lblSubtotal2 = new JLabel("0");
		lblSubtotal2.setBounds(166, 524, 110, 16);
		panel.add(lblSubtotal2);
		
		lblIva2 = new JLabel("0");
		lblIva2.setBounds(166, 564, 110, 16);
		panel.add(lblIva2);
		
		lblTotal2 = new JLabel("0");
		lblTotal2.setBounds(166, 604, 110, 16);
		panel.add(lblTotal2);
		
		comboBoxValidez = new JComboBox();
		comboBoxValidez.setBounds(166, 86, 76, 27);
		panel.add(comboBoxValidez);
		comboBoxValidez.addItem("7");
		
		lblDescuento = new JLabel("DESCUENTO");
		lblDescuento.setBounds(10, 484, 101, 16);
		panel.add(lblDescuento);
		
		comboBoxDescuento = new JComboBox();
		comboBoxDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTable1.getRowCount()>0 ) {
					int indexLista=comboBoxListaPrecios.getSelectedIndex(); //LE AGREGO -1 porque le agregué a el combo la frase NINGUNO
					if(indexLista!=0) {
						int ganancia = listaPrecios.get(indexLista-1).getDescuento();
						float porcentajeGanancia=Float.valueOf((100+ganancia))/100;
						if(!comboBoxDescuento.getSelectedItem().toString().equals("0 %")) {
							float descuentoAdicional=(100-Float.valueOf(comboBoxDescuento.getSelectedItem().toString().replace(" %", "")))/100;
							String producto="";
							float cantidad=0;
							float precio=0;
							float subtotal=0;
							float iva=0;
							for(int i=0;i<jTable1.getRowCount();i++) {
								producto=jTable1.getValueAt(i, 0).toString();
								cantidad=Float.valueOf(jTable1.getValueAt(i, 1).toString());
								precio=Controlador.getInstancia().obtenerPrecioProducto(producto)*porcentajeGanancia*descuentoAdicional;
								jTable1.setValueAt(formatearImporte(precio), i, 2);
								jTable1.setValueAt(formatearImporte(precio*cantidad), i, 3);
								subtotal+=(precio*cantidad);
							}
							iva=(float) (subtotal*0.21);
							lblSubtotal2.setText(formatearImporte(subtotal));
							lblIva2.setText(formatearImporte(iva));
							lblTotal2.setText(formatearImporte(subtotal+iva));
							btnAgregar.setEnabled(false);
							btnEliminar.setEnabled(false);
							
						}else {
							String producto="";
							float cantidad=0;
							float precio=0;
							float subtotal=0;
							float iva=0;
							for(int i=0;i<jTable1.getRowCount();i++) {
								producto=jTable1.getValueAt(i, 0).toString();
								cantidad=Float.valueOf(jTable1.getValueAt(i, 1).toString());
								precio=Controlador.getInstancia().obtenerPrecioProducto(producto)*porcentajeGanancia;
								jTable1.setValueAt(formatearImporte(precio), i, 2);
								jTable1.setValueAt(formatearImporte(precio*cantidad), i, 3);
								subtotal+=(precio*cantidad);
								
							}
							iva=(float) (subtotal*0.21);
							lblSubtotal2.setText(formatearImporte(subtotal));
							lblIva2.setText(formatearImporte(iva));
							lblTotal2.setText(formatearImporte(subtotal+iva));
							btnAgregar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
					
					}
					}
				
			}
		});
		comboBoxDescuento.setBounds(166, 480, 82, 27);
		panel.add(comboBoxDescuento);
		
		btnDescuentoSupervisor = new JButton("DESCUENTO SUPERVISOR");
		btnDescuentoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LiberarDescuento v=new LiberarDescuento();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnDescuentoSupervisor.setBounds(326, 479, 200, 29);
		panel.add(btnDescuentoSupervisor);
		
		lblFormaDePago = new JLabel("FORMA DE PAGO");
		lblFormaDePago.setBounds(293, 90, 131, 16);
		panel.add(lblFormaDePago);
		
		comboBoxFormaDePago = new JComboBox();
		comboBoxFormaDePago.setBounds(436, 86, 201, 27);
		panel.add(comboBoxFormaDePago);
		comboBoxFormaDePago.addItem("Ninguno");
		List<String> formasDePago=Controlador.getInstancia().obtenerFormasDePago();
		Collections.sort(formasDePago);
		for(int i=0;i<formasDePago.size();i++) {
			comboBoxFormaDePago.addItem(formasDePago.get(i));
		}

		int descuentoAutorizado=Controlador.getInstancia().obtenerDescuentoAutorizado();
		for(int i=0;i<=descuentoAutorizado;i++) {
			comboBoxDescuento.addItem(i + " %");
		}
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrarPrexpansion);
        
        
        modelocentrarPrexpansion.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getTableHeader().setDefaultRenderer(modelocentrarPrexpansion);
	}
	
	private String formatearImporte(float precio) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(precio);
	}

	private String generarFecha() {
		Date date = new java.util.Date(); 
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(date);
		return fecha;
	}
	public void setCliente(String nombre) {
		comboBoxClientes.setSelectedItem(nombre);
		
	}
	public void setProducto(String producto) {
		comboBoxProductos.setSelectedItem(producto);
		
	}
	public void setListaPrecio(String lista) {
		comboBoxListaPrecios.setSelectedItem(lista);
	}
	public void actualizarDescuentoSupervisor() {
		int descuentoSupervisor=Controlador.getInstancia().obtenerDescuentoSupervisor();
		int cantidad=comboBoxDescuento.getItemCount();
		for(int i=cantidad;i<=descuentoSupervisor;i++) {
			comboBoxDescuento.addItem(i+" %");
		}
		
	}
}
