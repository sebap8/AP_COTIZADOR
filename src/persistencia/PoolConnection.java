package persistencia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class PoolConnection {	

	private static PoolConnection instancia;
	private Vector <Connection> connections = new Vector<Connection>();
	private int cantCon = 4;
	
	private PoolConnection (){
		for (int i=0;i<cantCon;i++)
			connections.add(connect());			
	}
	
	public static PoolConnection getPoolConnection(){
		if (instancia == null)
			instancia = new PoolConnection();
		return instancia;
	}
	
	public Connection connect(){
		try{		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+leerIp(),"root","seba33");
			return con;
		}
		catch(SQLException e){
			System.out.println(" "+e.getMessage());
			System.out.println(" "+e.getStackTrace());
			return null;
		}
		catch(Exception ex){
			System.out.println(" "+ex.getMessage());
			System.out.println(" "+ex.getStackTrace());
			return null;
		}
	}
	
	public void closeConnections (){
		for (int i=0; i<connections.size();i++){
			try{
				connections.elementAt(i).close();
			}
			catch(SQLException e){
				System.out.println(" "+e.getMessage());
				System.out.println(" "+e.getStackTrace());
			}
			catch(Exception ex){
				System.out.println(" "+ex.getMessage());
				System.out.println(" "+ex.getStackTrace());
			}
		}
	}
	
	public void realeaseConnection(Connection c){
		connections.add(c);
	}
	
	public Connection getConnection(){
		Connection c;
		if(connections.size()>0)
			c = connections.remove(0);
		else{
			c = connect();
			System.out.println("Se ha creado una nueva conexion fuera de los parametros de configuracion");
		}
		return c;
	}
	private String leerIp(){
		String resultado="";
		String sCadena="";
		try{
			InputStream is = getClass().getResourceAsStream("/bd.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((sCadena = br.readLine())!=null) {
				   resultado=sCadena;
				}
		}catch(Exception e){
			System.out.println(" "+e.getMessage());
			System.out.println(" "+e.getStackTrace());
		}
		return resultado;
	}
}