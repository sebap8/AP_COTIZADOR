package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import beans.ListaPrecio;
import beans.ProductoBean;
import beans.ProductoPrecio;
import utils.Estado;

public class PersistenciaProducto {

	public static boolean agregarProducto(String nombre, float m3, int stockMinimo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idProducto=obtenermaxIdProducto();
			s.executeUpdate("insert into productos values("+idProducto+",'"+nombre+"',0,'Panel','"+Estado.PRODUCTO_ACTIVO+"',0,'"+m3+"',"+stockMinimo+")");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}

	private static int obtenermaxIdProducto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from productos");
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

	public static Vector<String> obtenerProductosBusqueda(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> v=null;
		try
		{
			Statement s=con.createStatement();
			v=new Vector<String>();
			ResultSet r=s.executeQuery("select Nombre from productos where nombre like '%"+nombre+"%' and estado='"+Estado.PRODUCTO_ACTIVO+"'");
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

	public static Vector<String> obtenerProductos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> v=null;
		try
		{
			Statement s=con.createStatement();
			v=new Vector<String>();
			ResultSet r=s.executeQuery("select * from productos where estado='"+Estado.PRODUCTO_ACTIVO+"'");
			while (r.next()){
				v.add(r.getString(2));
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return v;
	}

	public static ProductoBean obtenerProducto(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		ProductoBean p= new ProductoBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from productos where nombre like '%"+nombre+"%' and tipo='Panel'");
			while (r.next()){
				p.setNombre(r.getString(2));
				p.setStock(r.getInt(3));
				p.setM3(r.getFloat(7));
				p.setStockMinimo(r.getInt(8));
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return p;
	}

	public static boolean modificarProducto(String nombre, String stock, String m3, String stockMinimo,String productoAnterior) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update productos set nombre='"+nombre+"', stock="+stock+", m3="+m3+", stockMinimo="+stockMinimo+" where nombre='"+productoAnterior+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	}

	public static boolean eliminarProducto(String producto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			s.executeUpdate("update productos set estado='"+Estado.PRODUCTO_ELIMINADO+"' where nombre='"+producto+"'");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}

	public static List<ProductoPrecio> obtenerPreciosProductos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<ProductoPrecio> lista= new ArrayList<ProductoPrecio>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from productoPrecio ");
			while (r.next()){
				ProductoPrecio p=new ProductoPrecio();
				p.setId(r.getInt(1));
				p.setProducto(obtenerNombreProducto(r.getInt(2)));
				p.setPrecio(r.getFloat(3));
				lista.add(p);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return lista;
	}

	static String obtenerNombreProducto(int idProducto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String nombre="";
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select nombre from productos where id="+idProducto);
			while (r.next()){
				nombre=r.getString(1);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return nombre;
	}

	public static boolean actualizarListaDePrecios(List<ProductoPrecio> precios) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			for(int i=0;i<precios.size();i++) {
				s.executeUpdate("update productoPrecio set precio="+precios.get(i).getPrecio()+" where id="+precios.get(i).getId());
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

	public static boolean agregarListaPrecio(String nombre, String descripcion, String codigo, int descuento) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idListaPrecio=obtenermaxIdListaPrecio();
			s.executeUpdate("insert into listaPrecio values("+idListaPrecio+",'"+nombre+"','"+descripcion+"','"+codigo+"',"+descuento+",'"+Estado.LISTA_ACTIVA+"')");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}

	private static int obtenermaxIdListaPrecio() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from listaprecio");
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

	public static List<ListaPrecio> obtenerListasDePrecios() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<ListaPrecio> lista=new ArrayList<ListaPrecio>();	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from listaprecio where estado='"+Estado.LISTA_ACTIVA+"'");
			while (r.next()){
				ListaPrecio l=new ListaPrecio();
				l.setNombre(r.getString(2));
				l.setDescripcion(r.getString(3));
				l.setCodigo(r.getString(4));
				l.setDescuento(r.getInt(5));
				lista.add(l);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return lista;
	
	}

	public static List<ListaPrecio> obtenerListaDePrecios(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		List<ListaPrecio> lista=new ArrayList<ListaPrecio>();	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from listaprecio where estado='"+Estado.LISTA_ACTIVA+"' and nombre like '%"+nombre+"%'");
			while (r.next()){
				ListaPrecio l=new ListaPrecio();
				l.setNombre(r.getString(2));
				l.setDescripcion(r.getString(3));
				l.setCodigo(r.getString(4));
				l.setDescuento(r.getInt(5));
				lista.add(l);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return  lista;
	
	}

	public static float obtenerprecioProducto(String producto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		float precio=0;
		try
		{
			Statement s=con.createStatement();
			int idProducto=obtenerIdProducto(producto);
			ResultSet r=s.executeQuery("select precio from productoprecio where idProducto="+idProducto);
			while (r.next()){
				precio=r.getFloat(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return  precio;
	
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

	public static boolean guardarImagen(byte[] imagen,String nombreProducto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idFotoProducto=obtenermaxIdPrecioProducto();
			int idProducto=obtenerIdProducto(nombreProducto);
			if(verificarExistencia(idProducto)) {
				s.executeUpdate("update fotoproducto set foto='"+imagen+"'  where idProducto="+idProducto);
			}else {
				s.executeUpdate("insert into fotoproducto values("+idFotoProducto+","+idProducto+",'"+imagen+"')");
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

	private static boolean verificarExistencia(int idProducto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select idProducto from fotoproducto where idProducto="+idProducto);
			while (r.next()){
				flag=true;
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
	}

	private static int obtenermaxIdPrecioProducto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from fotoproducto");
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

	public static String obtenerCodigoListaPrecio(String listaPrecio) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String codigo="";	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select codigo from listaprecio where nombre='"+listaPrecio+"'");
			while (r.next()){
				codigo=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return codigo;
	
	}

	public static byte[] obtenerImagen(String producto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		byte[] result = null;
		try
		{
			Statement s=con.createStatement();
			int idProducto=obtenerIdProducto(producto);
			ResultSet r=s.executeQuery("select foto from  fotoproducto where idProducto="+idProducto);
			while (r.next()){
				result=r.getBytes(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return result;
	}

	public static boolean guardarImagenFinal(String ruta,String producto){
		 String insert = "insert into fotoProducto(id,idProducto,foto) values(?,?,?)";
		 FileInputStream fis = null;
		 PreparedStatement ps = null;
		 int idFotoProducto=obtenermaxIdPrecioProducto();
		 int idProducto=obtenerIdProducto(producto);
		 Connection con=PoolConnection.getPoolConnection().getConnection();
		 try {
		 con.setAutoCommit(false);
		 File file = new File(ruta);
		 fis = new FileInputStream(file);
		 ps = (PreparedStatement) con.prepareStatement(insert);
		 ps.setInt(1, idFotoProducto);
		 ps.setInt(2, idProducto);
		 ps.setBinaryStream(3,fis,(int)file.length());
		 ps.executeUpdate();
		 con.commit();
		 PoolConnection.getPoolConnection().realeaseConnection(con);
		 return true;
		 } catch (Exception ex) {
			 System.out.println(ex.getMessage());
				System.out.println(ex.getStackTrace());
		 }finally{
		 try {
		 ps.close();
		 fis.close();
		 } catch (Exception ex) {
			 System.out.println(ex.getMessage());
				System.out.println(ex.getStackTrace());
		 }
		 }        
		 return false;
		}

	public static String obtenerNombreListaPrecio(int idLista) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String nombre="";	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select nombre from listaprecio where id="+idLista);
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
