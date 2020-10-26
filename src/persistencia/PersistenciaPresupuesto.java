package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import beans.ItemPresupuestoBean;
import beans.PresupuestoBean;
import utils.Estado;

public class PersistenciaPresupuesto {

	public static boolean generarPresupuesto(PresupuestoBean p) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idCliente=obtenerIdCliente(p.getCliente());
			int idListaPrecio=obtenerIdLista(p.getListaPrecio());
			int idUsuario=obtenerIdUsuario(p.getResponsable());
			int idFormaDePago = PersistenciaParametrizacion.obtenerIdFormaDePago(p.getFormaDePago());
			s.executeUpdate("insert into presupuesto values("+p.getNumero()+",'"+p.getFecha()+"','"+p.getFechaVencimiento()+"',"+idCliente+","+idListaPrecio+","+idFormaDePago+","+p.getSubtotal()+","+p.getDescuento()+","+p.getIva()+","+p.getTotal()+","+idUsuario+",'"+Estado.PRESUPUESTO_ACTIVO+"')");
			List<ItemPresupuestoBean> items=p.getItems();
			for(int i=0;i<items.size();i++) {
				ItemPresupuestoBean it=items.get(i);
				int idItem=obtenerMaxIdItemPresupuesto();
				int idProducto=obtenerIdProducto(it.getProducto());
				s.execute("insert into itemPresupuesto values("+idItem+","+p.getNumero()+","+idProducto+","+it.getCantidad()+","+it.getPrecio()+")");
			}
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	}

	private static int obtenerIdProducto(String producto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from productos where nombre='"+producto+"'");
			while (r.next()){
				id=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	private static int obtenerMaxIdItemPresupuesto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from itemPresupuesto");
			while (r.next()){
				id=r.getInt(1);
			}
			id++;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	private static int obtenerIdUsuario(String responsable) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from usuarios where usuario='"+responsable+"'");
			while (r.next()){
				id=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	private static int obtenerIdLista(String listaPrecio) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from listaPrecio where nombre='"+listaPrecio+"'");
			while (r.next()){
				id=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	private static int obtenerIdCliente(String cliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from clientes where razonSocial='"+cliente+"'");
			while (r.next()){
				id=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	public static int obtenerNumeroPresupuesto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from presupuesto");
			while (r.next()){
				id=r.getInt(1);
			}
			id++;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	public static List<PresupuestoBean> obtenerPresupuestosActivos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<PresupuestoBean> presupuestos=new ArrayList<PresupuestoBean>();
		try
		{
			Statement s=con.createStatement();
			int fechaActual=obtenerFechaActual();
			ResultSet r=s.executeQuery("select * from presupuesto where estado='"+Estado.PRESUPUESTO_ACTIVO+"' and fechaVencimiento>="+fechaActual);
			while (r.next()){
				PresupuestoBean p=new PresupuestoBean();
				p.setCliente(PersistenciaCliente.obtenerNombreCliente(r.getInt(4)));
				p.setDescuento(r.getInt(8));
				p.setFecha(formatearFecha(r.getString(2)));
				p.setFechaVencimiento(formatearFecha(r.getString(3)));
				p.setFormaDePago(PersistenciaParametrizacion.obtenerNombreFormaPago(r.getInt(6)));
				p.setNumero(r.getInt(1));
				p.setItems(obtenerItemsPresupuesto(p.getNumero()));
				p.setIva(r.getFloat(9));
				p.setListaPrecio(PersistenciaProducto.obtenerNombreListaPrecio(r.getInt(5)));
				p.setResponsable(PersistenciaUsuario.obtenerNombreUsuario(r.getInt(11)));
				p.setSubtotal(r.getFloat(7));
				p.setTotal(r.getFloat(10));
				int dias=obtenerDiferenciaDias(p.getFecha(),p.getFechaVencimiento());

				p.setValidez(dias);
				
				presupuestos.add(p);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return presupuestos;
	
	}
	private static int obtenerFechaActual() {
		Date date = new java.util.Date(); 
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyyMMdd");
		return Integer.valueOf(sdf.format(date));
	}

	private static int obtenerDiferenciaDias(String fecha, String fechaVencimiento) {
		String[] fe=fecha.split("/");

		
		Calendar c = Calendar.getInstance();
		 
		Calendar fechaInicio = new GregorianCalendar();
		Calendar fechaFin = new GregorianCalendar();
		int dia=Integer.valueOf(fe[0]);
		int mes=Integer.valueOf(fe[1]);
		int anio=Integer.valueOf(fe[2]);
		 
		fechaInicio.set(anio, mes, dia);
		 
		fe=fechaVencimiento.split("/");
		dia=Integer.valueOf(fe[0]);
		mes=Integer.valueOf(fe[1]);
		anio=Integer.valueOf(fe[2]);
		 
		fechaFin.set(anio, mes, dia);
		 
		c.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());

		return c.get(Calendar.DAY_OF_YEAR);
	}

	private static List<ItemPresupuestoBean> obtenerItemsPresupuesto(int numero) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<ItemPresupuestoBean> items=new ArrayList<ItemPresupuestoBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from itemPresupuesto where idPresupuesto="+numero);
			while (r.next()){
				ItemPresupuestoBean it=new ItemPresupuestoBean();
				it.setCantidad(r.getInt(4));
				it.setPrecio(r.getFloat(5));
				it.setProducto(PersistenciaProducto.obtenerNombreProducto(r.getInt(3)));
				items.add(it);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return items;
	
	}

	private String formatearImporte(float precio) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(precio);
	}
	private static String formatearFecha(String fecha) {
		char[] result = {'a','a','/','a','a','/','a','a','a','a'};
		fecha.getChars(6, 7, result, 0);
		fecha.getChars(7, 8, result, 1);
		fecha.getChars(5, 6, result, 4);
		fecha.getChars(4, 5, result, 3);
		fecha.getChars(0, 1, result, 6);
		fecha.getChars(1, 2, result, 7);
		fecha.getChars(2, 3, result, 8);
		fecha.getChars(3, 4, result, 9);
		return String.valueOf(result);
		
	}

	public static boolean aceptarPresupuesto(int numeroPresupuesto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update presupuesto set estado='"+Estado.PRESUPUESTO_ACEPTADO+"' where id="+numeroPresupuesto);
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}

	public static List<PresupuestoBean> obtenerPresupuestosAceptados() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<PresupuestoBean> presupuestos=new ArrayList<PresupuestoBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from presupuesto where estado='"+Estado.PRESUPUESTO_ACEPTADO+"'");
			while (r.next()){
				PresupuestoBean p=new PresupuestoBean();
				p.setCliente(PersistenciaCliente.obtenerNombreCliente(r.getInt(4)));
				p.setDescuento(r.getInt(8));
				p.setFecha(formatearFecha(r.getString(2)));
				p.setFechaVencimiento(formatearFecha(r.getString(3)));
				p.setFormaDePago(PersistenciaParametrizacion.obtenerNombreFormaPago(r.getInt(6)));
				p.setNumero(r.getInt(1));
				p.setItems(obtenerItemsPresupuesto(p.getNumero()));
				p.setIva(r.getFloat(9));
				p.setListaPrecio(PersistenciaProducto.obtenerNombreListaPrecio(r.getInt(5)));
				p.setResponsable(PersistenciaUsuario.obtenerNombreUsuario(r.getInt(11)));
				p.setSubtotal(r.getFloat(7));
				p.setTotal(r.getFloat(10));
//				p.setValidez(validez);
				
				presupuestos.add(p);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return presupuestos;
	
	}

	public static List<PresupuestoBean> obtenerPresupuestosVencidos() {
		int fechaActual=obtenerFechaActual();
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<PresupuestoBean> presupuestos=new ArrayList<PresupuestoBean>();
		try
		{
			Statement s=con.createStatement();

			ResultSet r=s.executeQuery("select * from presupuesto where fechavencimiento<"+fechaActual);
			while (r.next()){
				PresupuestoBean p=new PresupuestoBean();
				p.setCliente(PersistenciaCliente.obtenerNombreCliente(r.getInt(4)));
				p.setDescuento(r.getInt(8));
				p.setFecha(formatearFecha(r.getString(2)));
				p.setFechaVencimiento(formatearFecha(r.getString(3)));
				p.setFormaDePago(PersistenciaParametrizacion.obtenerNombreFormaPago(r.getInt(6)));
				p.setNumero(r.getInt(1));
				p.setItems(obtenerItemsPresupuesto(p.getNumero()));
				p.setIva(r.getFloat(9));
				p.setListaPrecio(PersistenciaProducto.obtenerNombreListaPrecio(r.getInt(5)));
				p.setResponsable(PersistenciaUsuario.obtenerNombreUsuario(r.getInt(11)));
				p.setSubtotal(r.getFloat(7));
				p.setTotal(r.getFloat(10));
//				p.setValidez(validez);
				
				presupuestos.add(p);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return presupuestos;
	
	
	}

}
