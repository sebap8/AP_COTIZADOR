package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import beans.UsuarioBean;
import utils.Estado;

public class PersistenciaUsuario {

	public static boolean agregarUsuario(String nombre, String apellido, String email) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idUsuario=obtenermaxIdUsuario();
			String usuario=nombre.toUpperCase().charAt(0)+apellido;
			s.executeUpdate("insert into usuarios values("+idUsuario+",'"+nombre+"','"+apellido+"','"+usuario+"','"+email+"','1234','"+Estado.USUARIO_ACTIVO+"')");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	private static int obtenermaxIdUsuario() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from usuarios");
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
	public static Vector<String> obtenerUsuarios() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> usuarios=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select usuario from usuarios where estado='"+Estado.USUARIO_ACTIVO+"'");
			while (r.next()){
				usuarios.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return usuarios;
	}
	public static boolean esUsuarioNuevo(String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String contrasenia="";	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select contrasena from usuarios where usuario='"+usuario+"'");
			while (r.next()){
				contrasenia=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		if(contrasenia.equals("1234")) {
			return true;
		}else {
			return false;
		}
	
	}
	public static boolean verificarContrasenia(String usuario, int contrasena) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select usuario,contrasena from usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"'");
			while (r.next()){
				if(r.getString(1)!=null) {
					flag=true;
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	}
	public static boolean RegistrarNuevaContrasena(String usuario, int pass) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update usuarios set contrasena='"+pass+"' where usuario='"+usuario+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	public static Vector<String> obtenerUsuariosCotizadorPanel() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> resultado=new Vector<String>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select usuario from usuarios where estado='"+Estado.USUARIO_ACTIVO+"'");
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
	public static String obtenerIdUsuario(String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String id="0";	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from usuarios where usuario='"+usuario+"'");
			while (r.next()){
				id=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}
	public static UsuarioBean obtenerUsuario(String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		UsuarioBean usu=new UsuarioBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from usuarios where usuario='"+usuario+"'");
			while (r.next()){
			usu.setNombre(r.getString(2));
			usu.setApellido(r.getString(3));
			usu.setEmail(r.getString(5));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return usu;
	
	}
	public static boolean modificarUsuario(String nombre, String apellido, String mail, String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update usuarios set nombre='"+nombre+"', apellido='"+apellido+"', email='"+mail+"' where usuario='"+usuario+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	public static boolean eliminarUsuario(String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update usuarios set estado='"+Estado.USUARIO_ELIMINADO+"' where usuario='"+usuario+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	public static String obtenerNombreUsuario(int idUsuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String nombre="";
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select nombre,apellido from usuarios where id="+idUsuario);
			while (r.next()){
			nombre=r.getString(1)+" "+r.getString(2);
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
