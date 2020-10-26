package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.Estado;

public class PersistenciaParametrizacion {

	public static boolean actualizarDescuentos(int descAutorizado, int descSupervisor) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update descuentos set autorizado="+descAutorizado+", supervisor="+descSupervisor+" where id=1");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	
	}

	public static int obtenerDescuentoAutorizado() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int descuento=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select autorizado from descuentos where id=1");
			while (r.next()){
				descuento=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return descuento;
	}

	public static int obtenerDescuentoSupervisor() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int descuento=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select supervisor from descuentos where id=1");
			while (r.next()){
				descuento=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return descuento;
	}

	public static List<String> obtenerFormasDePago() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<String> resultado= new ArrayList<String>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select descripcion from formadepago where estado='"+Estado.FORMA_DE_PAGO_ACTIVA+"'");
			while (r.next()){
				resultado.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	}

	public static int obtenerIdFormaDePago(String formaDePago) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int resultado= 0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from formadepago where descripcion='"+formaDePago+"'");
			while (r.next()){
				resultado=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	}

	public static String obtenerNombreFormaPago(int idFormaPago) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String resultado= "";
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select descripcion from formadepago where id="+idFormaPago);
			while (r.next()){
				resultado=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	}

}
