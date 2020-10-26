package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PresupuestoBean {
	private String fecha;
	private int numero;
	private int validez;
	private String cliente;
	private String listaPrecio;
	private float subtotal;
	private float iva;
	private float total;
	private String formaDePago;
	private int descuento;
	private List<ItemPresupuestoBean> items=new ArrayList<ItemPresupuestoBean>();
	
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	//Auxiliares para persistencia
	private String fechaVencimiento;
	private String responsable;
	
	public List<ItemPresupuestoBean> getItems() {
		return items;
	}
	public void setItems(List<ItemPresupuestoBean> items) {
		this.items = items;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getValidez() {
		return validez;
	}
	public void setValidez(int validez) {
		this.validez = validez;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getListaPrecio() {
		return listaPrecio;
	}
	public void setListaPrecio(String listaPrecio) {
		this.listaPrecio = listaPrecio;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public Vector<ItemPresupuestoBean> getItemsVector() {
		Vector<ItemPresupuestoBean> its=new Vector<ItemPresupuestoBean>();
		for(int i=0;i<items.size();i++) {
			ItemPresupuestoBean itAux=items.get(i);
			ItemPresupuestoBean it=new ItemPresupuestoBean();
			it.setCantidad(itAux.getCantidad());
			it.setPrecio(itAux.getPrecio());
			it.setProducto(itAux.getProducto());
			its.add(it);
		}
		return its;
	}
	
	

}
