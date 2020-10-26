package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.directory.ModificationItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import utils.Parametrizacion;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	Vector<String> permisos=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("SISTEMA DE PRESUPUESTOS DE PANELES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		permisos=Controlador.getInstancia().obtenerPermisos();
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnInicio.add(mntmSalir);
		
		JMenu mnPresupuestos = new JMenu("Presupuestos");
		menuBar.add(mnPresupuestos);
		
		JMenuItem mntmNuevoPresupuesto = new JMenuItem("Nuevo presupuesto");
		mntmNuevoPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarPresupuesto.setInstancia();
				GenerarPresupuesto v= GenerarPresupuesto.getInstancia();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		if(verificarPermiso(Parametrizacion.AGREGAR_PRESUPUESTO)){
			mntmNuevoPresupuesto.setEnabled(true);
		}else {
			mntmNuevoPresupuesto.setEnabled(false);
		}

		mnPresupuestos.add(mntmNuevoPresupuesto);
		
		JMenuItem mntmModificarPresupuesto = new JMenuItem("Modificar presupuesto");
		mntmModificarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		if(verificarPermiso(Parametrizacion.MODIFICAR_PRESUPUESTO)){
			mntmModificarPresupuesto.setEnabled(true);
		}else {
			mntmModificarPresupuesto.setEnabled(false);
		}
		mnPresupuestos.add(mntmModificarPresupuesto);
		
		JMenuItem mntmEliminarPresupuesto = new JMenuItem("Eliminar presupuesto");
		mnPresupuestos.add(mntmEliminarPresupuesto);
		
		if(verificarPermiso(Parametrizacion.ELIMINAR_PRESUPUESTO)){
			mntmEliminarPresupuesto.setEnabled(true);
		}else {
			mntmEliminarPresupuesto.setEnabled(false);
		}
		
		JMenuItem mntmPresupuestosActivos = new JMenuItem("Presupuestos activos");
		mntmPresupuestosActivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoPresupuestosActivos listado=new ListadoPresupuestosActivos();
				listado.setLocationRelativeTo(null);
				listado.setVisible(true);
			}
		});
		mnPresupuestos.add(mntmPresupuestosActivos);
		if(verificarPermiso(Parametrizacion.PRESUPUESTOS_ACTIVOS)){
			mntmPresupuestosActivos.setEnabled(true);
		}else {
			mntmPresupuestosActivos.setEnabled(false);
		}
		
		JMenuItem mntmPresupuestosAceptados = new JMenuItem("Presupuestos aceptados");
		mntmPresupuestosAceptados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoPresupuestosAceptados listado=new ListadoPresupuestosAceptados();
				listado.setLocationRelativeTo(null);
				listado.setVisible(true);
			}
		});
		mnPresupuestos.add(mntmPresupuestosAceptados);
		if(verificarPermiso(Parametrizacion.PRESUPUESTOS_ACEPTADOS)){
			mntmPresupuestosAceptados.setEnabled(true);
		}else {
			mntmPresupuestosAceptados.setEnabled(false);
		}
		
		JMenuItem mntmPresupuestosVencidos = new JMenuItem("Presupuestos vencidos");
		mntmPresupuestosVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnPresupuestos.add(mntmPresupuestosVencidos);
		if(verificarPermiso(Parametrizacion.PRESUPUESTOS_VENCIDOS)){
			mntmPresupuestosVencidos.setEnabled(true);
		}else {
			mntmPresupuestosVencidos.setEnabled(false);
		}

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmAgregarCliente = new JMenuItem("Agregar cliente");
		mntmAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCliente v= new AgregarCliente();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
				
			}
		});
		mnClientes.add(mntmAgregarCliente);
		if(verificarPermiso(Parametrizacion.AGREGAR_CLIENTE)){
			mntmAgregarCliente.setEnabled(true);
		}else {
			mntmAgregarCliente.setEnabled(false);
		}
		
		JMenuItem mntmModificarCliente = new JMenuItem("Modificar cliente");
		mntmModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarCliente v = ModificarCliente.getInstancia();
				v.setLocationRelativeTo(null);
				v.setVisible(true);	
			}
		});
		mnClientes.add(mntmModificarCliente);
		if(verificarPermiso(Parametrizacion.MODIFICAR_CLIENTE)){
			mntmModificarCliente.setEnabled(true);
		}else {
			mntmModificarCliente.setEnabled(false);
		}
		
		JMenuItem mntmEliminarCliente = new JMenuItem("Eliminar cliente");
		mntmEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarCliente v=EliminarCliente.getInstancia();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnClientes.add(mntmEliminarCliente);
		if(verificarPermiso(Parametrizacion.ELIMINAR_CLIENTE)){
			mntmEliminarCliente.setEnabled(true);
		}else {
			mntmEliminarCliente.setEnabled(false);
		}
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenuItem mntAgregarProducto = new JMenuItem("Agregar producto");
		mntAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarProducto a=new AgregarProducto();
				a.setLocationRelativeTo(null);
				a.setVisible(true);
			}
		});
		mnProductos.add(mntAgregarProducto);
		if(verificarPermiso(Parametrizacion.AGREGAR_PRODUCTO)){
			mntAgregarProducto.setEnabled(true);
		}else {
			mntAgregarProducto.setEnabled(false);
		}
		
		JMenuItem mntModificarProducto = new JMenuItem("Modificar producto");
		mntModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarProducto v = ModificarProducto.getInstancia();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnProductos.add(mntModificarProducto);
		if(verificarPermiso(Parametrizacion.MODIFICAR_PRODUCTO)){
			mntModificarProducto.setEnabled(true);
		}else {
			mntModificarProducto.setEnabled(false);
		}
		
		JMenuItem mntmEliminarProducto = new JMenuItem("Eliminar producto");
		mntmEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProducto v=EliminarProducto.getInstancia();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnProductos.add(mntmEliminarProducto);
		if(verificarPermiso(Parametrizacion.ELIMINAR_PRODUCTO)){
			mntmEliminarProducto.setEnabled(true);
		}else {
			mntmEliminarProducto.setEnabled(false);
		}
		
		JMenu mnListasPrecios = new JMenu("Listas de precios");
		menuBar.add(mnListasPrecios);
		
		JMenuItem mntmListaDePrecios = new JMenuItem("Lista de precios madre");
		mntmListaDePrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPreciosMadre v=new ListaPreciosMadre();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnListasPrecios.add(mntmListaDePrecios);
		if(verificarPermiso(Parametrizacion.LISTA_PRECIOS_MADRE)){
			mntmListaDePrecios.setEnabled(true);
		}else {
			mntmListaDePrecios.setEnabled(false);
		}
		
		JMenuItem mntmAgregarListaPrecio = new JMenuItem("Nueva lista");
		mntmAgregarListaPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarListaPrecio l= new AgregarListaPrecio();
				l.setLocationRelativeTo(null);
				l.setVisible(true);
			}
		});
		mnListasPrecios.add(mntmAgregarListaPrecio);
		if(verificarPermiso(Parametrizacion.NUEVA_LISTA)){
			mntmAgregarListaPrecio.setEnabled(true);
		}else {
			mntmAgregarListaPrecio.setEnabled(false);
		}
		JMenuItem mntmModificarListaPrecio = new JMenuItem("Modificar lista");
		mnListasPrecios.add(mntmModificarListaPrecio);
		if(verificarPermiso(Parametrizacion.MODIFICAR_LISTA)){
			mntmModificarListaPrecio.setEnabled(true);
		}else {
			mntmModificarListaPrecio.setEnabled(false);
		}
		JMenuItem mntmEliminarListaPrecio = new JMenuItem("Eliminar lista");
		mnListasPrecios.add(mntmEliminarListaPrecio);
		if(verificarPermiso(Parametrizacion.ELIMINAR_LISTA)){
			mntmEliminarListaPrecio.setEnabled(true);
		}else {
			mntmEliminarListaPrecio.setEnabled(false);
		}
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar usuario");
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarUsuario u=new AgregarUsuario();
				u.setLocationRelativeTo(null);
				u.setVisible(true);
			}
		});
		mnUsuarios.add(mntmAgregarUsuario);
		if(verificarPermiso(Parametrizacion.AGREGAR_USUARIO)){
			mntmAgregarUsuario.setEnabled(true);
		}else {
			mntmAgregarUsuario.setEnabled(false);
		}
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar usuario");
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario m = new ModificarUsuario();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
		mnUsuarios.add(mntmModificarUsuario);
		if(verificarPermiso(Parametrizacion.MODIFICAR_USUARIO)){
			mntmModificarUsuario.setEnabled(true);
		}else {
			mntmModificarUsuario.setEnabled(false);
		}
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar usuario");
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarUsuario v= new EliminarUsuario();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnUsuarios.add(mntmEliminarUsuario);
		if(verificarPermiso(Parametrizacion.ELIMINAR_USUARIO)){
			mntmEliminarUsuario.setEnabled(true);
		}else {
			mntmEliminarUsuario.setEnabled(false);
		}
		
		JMenuItem mntmParametrizar = new JMenuItem("Parametrizar");
		mntmParametrizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParametrizarUsuario v= new ParametrizarUsuario();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mnUsuarios.add(mntmParametrizar);
		if(verificarPermiso(Parametrizacion.PARAMETRIZAR_USUARIO)){
			mntmParametrizar.setEnabled(true);
		}else {
			mntmParametrizar.setEnabled(false);
		}
		
		JMenu mnPArametrizaciones = new JMenu("Parametrizacion");
		menuBar.add(mnPArametrizaciones);
		
		JMenuItem mntmFormasDePago = new JMenuItem("Formas de pago");
		mntmFormasDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnPArametrizaciones.add(mntmFormasDePago);
		if(verificarPermiso(Parametrizacion.FORMAS_DE_PAGO)){
			mntmFormasDePago.setEnabled(true);
		}else {
			mntmFormasDePago.setEnabled(false);
		}
		
		JMenuItem mntmDescuentosAClientes = new JMenuItem("Descuentos a clientes");
		mntmDescuentosAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DescuentoCliente descuento = new DescuentoCliente();
				descuento.setLocationRelativeTo(null);
				descuento.setVisible(true);
			}
		});
		mnPArametrizaciones.add(mntmDescuentosAClientes);
		if(verificarPermiso(Parametrizacion.DESCUENTOS_CLIENTES)){
			mntmDescuentosAClientes.setEnabled(true);
		}else {
			mntmDescuentosAClientes.setEnabled(false);
		}
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private boolean verificarPermiso(String nombre) {
		boolean flag=false;
		for(int i=0;i<permisos.size()&&!flag;i++){
			if(permisos.get(i).equals(nombre)){
				flag=true;
			}
		}
		return flag;
	}
}
