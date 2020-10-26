package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import utils.Estado;
import utils.Parametrizacion;

public class PersistenciaPermiso {
	
	public static Vector<String> obtenerPermisosOtrogados(String operario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from usuarios e, perfilPanel p where e.id=p.idusuario and e.usuario='"+operario+"'");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

	public static Vector<String> obtenerPermisosDisponibles(String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from usuarios e, perfilpanel p where e.id=p.idUsuario "
					+ "and e.usuario!='"+usuario+"' and p.nombre not in"
					+ "(select p1.nombre from usuarios e1, perfilpanel p1 where e1.id=p1.idUsuario and e1.usuario='"+usuario+"' )"
					+ "group by p.nombre;");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

	public static boolean cambiarPermisos(Vector<String> permisos, String usuario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();	
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			String idOperario=PersistenciaUsuario.obtenerIdUsuario(usuario);
			s.executeUpdate("delete from perfilpanel where idUsuario ="+idOperario);
			
			for(int i=0;i<permisos.size();i++){
				int idPerfil=obtenermaxIdPerfil();
				idPerfil++;
				s.executeUpdate("insert into perfilpanel values("+idPerfil+",'"+permisos.get(i)+"',"+idOperario+")");
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

	private static int obtenermaxIdPerfil() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from perfilpanel");
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

	public static Vector<String> obtenerPermisos(String usuarioActual) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from perfilpanel p, usuarios u where u.id=p.idusuario and u.usuario='"+usuarioActual+"'");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

	public static List<String> obtenerCorreosMail() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> resultado=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select u.email from perfilpanel p, usuarios u where u.id=p.idusuario and p.nombre='"+Parametrizacion.EMAIL_NUEVO_PRESUPUESTO+"'");
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

	public static boolean verificarPermisoDescuento(String usuario, String contrasenia) {
		int pass=contrasenia.hashCode();
		boolean flag = PersistenciaUsuario.verificarContrasenia(usuario, pass);
		boolean bandera=false;
		Connection con=PoolConnection.getPoolConnection().getConnection();
		if(flag==true) {
			try {
				Statement s=con.createStatement();
				ResultSet r=s.executeQuery("select u.nombre from perfilpanel p, usuarios u where u.id=p.idusuario and u.usuario='"+usuario+"' and u.contrasena='"+pass+"' and p.nombre='"+Parametrizacion.DESCUENTO_SUPERVISOR+"'");
				while (r.next()){
					bandera=true;
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());
			}
		}
		return bandera;
	}
	


}
