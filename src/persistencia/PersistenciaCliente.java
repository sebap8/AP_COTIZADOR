package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import beans.ClienteBean;
import beans.ProductoBean;
import utils.Estado;

public class PersistenciaCliente {

	public static List<String> obtenerClientes() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> clientes=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select razonsocial from clientes where estado='"+Estado.CLIENTE_ACTIVO+"'");
			while (r.next()){
				clientes.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return clientes;
	}

	public static boolean agregarCliente(String cuit, String razonSocial, String domicilio, String provincia,
			String telefono, String contacto, String telefonoContacto, String email, String condicionIva) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idCliente=obtenermaxIdCliente();
			s.executeUpdate("insert into clientes values("+idCliente+",'"+Estado.CLIENTE_ACTIVO+"','"+cuit+"','"+razonSocial+"','"+domicilio+"','"+provincia+"','"+telefono+"','"+contacto+"','"+telefonoContacto+"','"+email+"','"+condicionIva+"')");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}

	private static int obtenermaxIdCliente() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from clientes");
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

	public static Vector<String> obtenerClientesBusqueda(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> v=null;
		try
		{
			Statement s=con.createStatement();
			v=new Vector<String>();
			ResultSet r=s.executeQuery("select razonsocial from clientes where razonsocial like '%"+nombre+"%' and estado='"+Estado.CLIENTE_ACTIVO+"'");
			while (r.next()){
				v.add(r.getString(1));
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return v;
	}

	public static ClienteBean obtenerCliente(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		ClienteBean c= new ClienteBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from clientes where razonsocial='"+nombre+"' and estado='"+Estado.CLIENTE_ACTIVO+"'");
			while (r.next()){
				if(r.getString(3)!=null) {
					c.setCuit(r.getString(3));
				}else {
					c.setCuit("");
				}
				c.setNombre(r.getString(4));
				if(r.getString(5)!=null) {
					c.setDomicilio(r.getString(5));
				}else {
					c.setDomicilio("");
				}
				if(r.getString(6)!=null) {
					c.setProvincia(r.getString(6));
				}else {
					c.setProvincia("");
				}
				if(r.getString(7)!=null) {
					c.setTelefono(r.getString(7));
				}else {
					c.setTelefono("");
				}
				if(r.getString(8)!=null) {
					c.setContacto(r.getString(8));
				}else {
					c.setContacto("");
				}
				if(r.getString(9)!=null) {
					c.setTelContacto(r.getString(9));
				}else {
					c.setTelContacto("");
				}
				if(r.getString(10)!=null) {
					c.setEmail(r.getString(10));
				}else {
					c.setEmail("");
				}
				if(r.getString(11)!=null) {
					c.setCondicionIVA(r.getString(11));
				}else {
					c.setCondicionIVA("");
				}

			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return c;
	}

	public static boolean modificarCliente(String cuit, String razonSocial, String domicilio, String provincia,
			String telefono, String contacto, String telefonoContacto, String email, String condicionIva,
			String razonSocialVieja) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update clientes set cuit='"+cuit+"',razonSocial='"+razonSocial+"', domicilio='"+domicilio+"', localidad='"+provincia+"', telefono='"+telefono+"',nombreContacto='"+contacto+"',telefonoContacto='"+telefonoContacto+"', mailContacto='"+email+"',condicionIva='"+condicionIva+"' where razonSocial='"+razonSocialVieja+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	}

	public static String obtenerUbicacion(String cliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String v="-";
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select localidad from clientes where razonsocial='"+cliente+"'");
			while (r.next()){
				v=r.getString(1);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return v;
	}

	public static String obtenerNombreCliente(int idCliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String nombre="";	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select razonsocial from clientes where id="+idCliente);
			while (r.next()){
				nombre=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return nombre;
	
	}

}
