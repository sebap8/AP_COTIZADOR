package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import beans.ClienteBean;
import beans.ListaPrecio;
import beans.PresupuestoBean;
import beans.ProductoBean;
import beans.ProductoPrecio;
import beans.UsuarioBean;
import persistencia.PersistenciaCliente;
import persistencia.PersistenciaParametrizacion;
import persistencia.PersistenciaPermiso;
import persistencia.PersistenciaPresupuesto;
import persistencia.PersistenciaProducto;
import persistencia.PersistenciaUsuario;
import utils.Parametrizacion;
import managers.EmailManager;
import managers.PDFManager;

public class Controlador {
	private static Controlador instancia;
	private String usuarioActual;
	
	public static Controlador getInstancia(){
		if(instancia==null){
			instancia=new Controlador();
		}
		return instancia;
	}

	public boolean agregarUsuario(String nombre, String apellido, String email) {
		return PersistenciaUsuario.agregarUsuario(nombre,apellido,email);
	}

	public Vector<String> obtenerUsuarios() {
		Vector<String> usuarios=PersistenciaUsuario.obtenerUsuarios();
		Collections.sort(usuarios);
		return usuarios;
	}

	public boolean esUsuarioNuevo(String usuario) {
		return PersistenciaUsuario.esUsuarioNuevo(usuario);
	}

	public boolean verificarContrasenia(String usuario, String contrasena) {
		int pass=contrasena.hashCode();
		boolean flag=PersistenciaUsuario.verificarContrasenia(usuario,pass);
		if(flag) {
			usuarioActual=usuario;
		}
		return flag;
	}

	public boolean registrarNuevaContrasena(String usuario, String contrasena) {
		int pass=contrasena.hashCode();
		return PersistenciaUsuario.RegistrarNuevaContrasena(usuario,pass);
	}

	public Vector<String> obtenerUsuariosCotizadorPanel() {
		return PersistenciaUsuario.obtenerUsuariosCotizadorPanel();
	}

	public Vector<String> obtenerPermisosOtrogados(String usuario) {
		return PersistenciaPermiso.obtenerPermisosOtrogados(usuario);
	}

	public Vector<String> obtenerPermisosDisponibles(String usuario) {
		return PersistenciaPermiso.obtenerPermisosDisponibles(usuario);
	}

	public boolean cambiarPermisos(Vector<String> permisos, String usuario) {
		return PersistenciaPermiso.cambiarPermisos(permisos,usuario);
	}

	public Vector<String> obtenerPermisos() {
		return PersistenciaPermiso.obtenerPermisos(usuarioActual);
	}

	public UsuarioBean obtenerUsuario(String usuario) {
		return PersistenciaUsuario.obtenerUsuario(usuario);
	}

	public boolean modificarUsuario(String nombre, String apellido, String mail,String usuario) {
		return PersistenciaUsuario.modificarUsuario(nombre,apellido,mail, usuario);
	}

	public boolean eliminarUsuario(String usuario) {
		return PersistenciaUsuario.eliminarUsuario(usuario);
	}

	public boolean agregarProducto(String nombre, float m3, int stockMinimo) {
		return PersistenciaProducto.agregarProducto(nombre,m3,stockMinimo);
	}

	public Vector<String> obtenerProductosBusqueda(String nombre) {
		return PersistenciaProducto.obtenerProductosBusqueda(nombre);
	}

	public Vector<String> obtenerProductos() {
		return PersistenciaProducto.obtenerProductos();
	}

	public ProductoBean obtenerProducto(String nombre) {
		return PersistenciaProducto.obtenerProducto(nombre);
	}

	public boolean modificarProducto(String nombre, String stock, String m3, String stockMinimo, String productoAnterior) {
		return PersistenciaProducto.modificarProducto(nombre,stock,m3,stockMinimo,productoAnterior);
	}

	public boolean eliminarProducto(String producto) {
		return PersistenciaProducto.eliminarProducto(producto);
	}

	public List<String> obtenerClientes() {
		return PersistenciaCliente.obtenerClientes();
	}

	public boolean agregarCliente(String cuit,String razonSocial, String domicilio, String provincia,String telefono,String contacto,String telefonoContacto,String email,String condicionIva) {
		if(contacto.isEmpty()) {
			contacto="";
		}
		if(telefonoContacto.isEmpty()) {
			telefonoContacto="";
		}
		return PersistenciaCliente.agregarCliente(cuit, razonSocial, domicilio, provincia, telefono, contacto, telefonoContacto, email, condicionIva);
	}

	public Vector<String> obtenerClientesBusqueda(String nombre) {
		return PersistenciaCliente.obtenerClientesBusqueda(nombre);
	}

	public ClienteBean obtenerCliente(String nombre) {
		return PersistenciaCliente.obtenerCliente(nombre);
	}

	public boolean modificarCliente(String cuit,String razonSocial, String domicilio, String provincia,String telefono,String contacto,String telefonoContacto,String email,String condicionIva,String razonSocialVieja) {
		if(contacto.isEmpty()) {
			contacto="";
		}
		if(telefonoContacto.isEmpty()) {
			telefonoContacto="";
		}
		return PersistenciaCliente.modificarCliente(cuit,razonSocial,domicilio,provincia,telefono,contacto,telefonoContacto,email,condicionIva,razonSocialVieja);
	}

	public List<ProductoPrecio> obtenerPreciosProductos() {
		return PersistenciaProducto.obtenerPreciosProductos();
	}

	public boolean actualizarListaDePrecios(List<ProductoPrecio> precios) {
		return PersistenciaProducto.actualizarListaDePrecios(precios);
	}

	public boolean agregarListaDePrecio(String nombre, String descripcion, String codigo, int descuento) {
		return PersistenciaProducto.agregarListaPrecio(nombre,descripcion,codigo,descuento);
	}

	public List<ListaPrecio> obtenerListasDePrecios() {
		return PersistenciaProducto.obtenerListasDePrecios();
	}

	public String obtenerNumeroPresupuesto() {
		int numeroPresupuesto=PersistenciaPresupuesto.obtenerNumeroPresupuesto();
		return String.format("%08d", numeroPresupuesto);	
	}

	public List<ListaPrecio> obtenerListasDePrecios(String nombre) {
		return PersistenciaProducto.obtenerListaDePrecios(nombre);
	}

	public float obtenerPrecioProducto(String producto) {
		return PersistenciaProducto.obtenerprecioProducto(producto);
	}

	public boolean generarPresupuesto(PresupuestoBean p) {
		Date fecha = new java.util.Date(); 
		fecha=sumarDiasAFecha(fecha, p.getValidez());
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
		p.setFechaVencimiento(transformarFechaBD(sdf.format(fecha)));
		p.setFecha(transformarFechaBD(p.getFecha()));
		p.setResponsable(usuarioActual);
		return PersistenciaPresupuesto.generarPresupuesto(p);
	}
	public static Date sumarDiasAFecha(Date fecha, int dias){
	      if (dias==0) return fecha;
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); 
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  
	      return calendar.getTime(); 
	}
	private String transformarFechaBD(String fecha) {
		String[] fe=fecha.split("/");
		return (fe[2]+fe[1]+fe[0]);
	}

	public void generarPDF(PresupuestoBean p) {

    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo XLS","PDF","Excel");
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setFileFilter(filter);
		selectorArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY );
		int resultado = selectorArchivo.showSaveDialog( null );
	    if ( resultado == JFileChooser.CANCEL_OPTION )
//		        return 0;
	    		System.out.println("ERROR");
		File nombreArchivo = selectorArchivo.getSelectedFile();
		if ( nombreArchivo == null || nombreArchivo.getName().equals( "" ) )
			JOptionPane.showMessageDialog( null, "Nombre de archivo incorrecto", 
			"Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE );
		else {
			 PDFManager generatePDFFileIText = new PDFManager();
			 generatePDFFileIText.createPDF(nombreArchivo,p);
		}
		
	}
	public void generarPDF2(PresupuestoBean p) {
		File nombreArchivo = new File(p.getNumero()+"_"+p.getCliente()+".pdf");
		PDFManager generatePDFFileIText = new PDFManager();
		generatePDFFileIText.createPDF(nombreArchivo,p);
		enviarMailsPresupuestoNuevo(p,nombreArchivo);
	}
	
	
	public void enviarMailsPresupuestoNuevo(PresupuestoBean p,File f) {
		List<String> correos=PersistenciaPermiso.obtenerCorreosMail();
		String cuerpo=" SE HA GENERADO UN NUEVO PRESUPUESTO \n \n"
				+ "PRESUPUESTO Nª: "+String.format("%08d", p.getNumero())+" \n"
						+ "CLIENTE: "+p.getCliente()+" \n";
		String asunto="AVISO SISTEMA COTIZADOR - NUEVO PRESUPUESTO";
		enviarMails(correos, asunto, cuerpo,f);
	}
	
	public void enviarMails(List<String> correos, String asunto,String cuerpo,File file) {
		for(int i=0;i<correos.size();i++) {
			new EmailManager(correos.get(i), asunto, cuerpo,file).start();
		}
	}

	public boolean guardarImagenProducto(byte[] imagen, String producto) {
		return PersistenciaProducto.guardarImagen(imagen, producto);
		
	}

	public boolean actualizarDescuentos(int descAutorizado, int descSupervisor) {
		return PersistenciaParametrizacion.actualizarDescuentos(descAutorizado,descSupervisor);
	}

	public int obtenerDescuentoAutorizado() {
		return PersistenciaParametrizacion.obtenerDescuentoAutorizado();
	}

	public int obtenerDescuentoSupervisor() {
		return PersistenciaParametrizacion.obtenerDescuentoSupervisor();
	}

	public boolean verificarPermisoDescuento(String usuario, String contrasenia) {
		return PersistenciaPermiso.verificarPermisoDescuento(usuario,contrasenia);
	}

	public List<String> obtenerFormasDePago() {
		return PersistenciaParametrizacion.obtenerFormasDePago();
	}

	public String obtenerCodigoListaPrecio(String listaPrecio) {
		return PersistenciaProducto.obtenerCodigoListaPrecio(listaPrecio);
	}

	public String obtenerUbicacionCliente(String cliente) {
		return PersistenciaCliente.obtenerUbicacion(cliente);
	}

	public byte[] obtenerImagen(String producto) {
		return PersistenciaProducto.obtenerImagen(producto);
	}

	public boolean guardarImagen(String ruta, String nombre) {
		return PersistenciaProducto.guardarImagenFinal(ruta,nombre);
	}

	public List<PresupuestoBean> obtenerPresupuestosActivos() {
		return PersistenciaPresupuesto.obtenerPresupuestosActivos();
	}

	public void verPresupuesto(PresupuestoBean presupuestoBean) {
		File nombreArchivo = new File(presupuestoBean.getNumero()+"_"+presupuestoBean.getCliente()+".pdf");
		PDFManager generatePDFFileIText = new PDFManager();
		generatePDFFileIText.createPDF(nombreArchivo,presupuestoBean);
		try {
			Desktop.getDesktop().open(nombreArchivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		try {
            Thread.sleep(3*1000);
         } catch (Exception e) {
            System.out.println(e);
         }
		nombreArchivo.delete();
	}

	public boolean aceptarPresupuesto(int numeroPresupuesto) {
		return PersistenciaPresupuesto.aceptarPresupuesto(numeroPresupuesto);
	}

	public List<PresupuestoBean> obtenerPresupuestosAceptados() {
		return PersistenciaPresupuesto.obtenerPresupuestosAceptados();
	}

	public List<PresupuestoBean> obtenerPresupuestosVencidos() {
		return PersistenciaPresupuesto.obtenerPresupuestosVencidos();
	}

}
	
