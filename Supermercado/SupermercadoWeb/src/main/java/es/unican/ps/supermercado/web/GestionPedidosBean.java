/**
 *
 * Author: Javier Barquin
 * Date: 16 dic. 2021
 *
 */
package es.unican.ps.supermercado.web;

import java.time.LocalTime;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.unican.ps.supermercado.businessLayer.IConsultaArticulosRemote;
import es.unican.ps.supermercado.businessLayer.IGestionPedidosRemote;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.LineaPedido;
import es.unican.ps.supermercado.entities.Pedido;

/**
 * @author barquinj
 *
 */
@Named
@RequestScoped
public class GestionPedidosBean {

	@EJB
	private IGestionPedidosRemote myGestionPedidos;
	@EJB
	private IConsultaArticulosRemote myConsultaArticulos;
	// pantallas
	private String pantallaArticulos = "articulos";
	private String pantallaRegistrarse = "registrarse";
	private String pantallaCarro = "carroCompra";
	private String pantallaRealizado = "pedidoRealizado";
	
	private String horaReco;
	private String dni;
	private Pedido myPedido;
	private Articulo articuloSeleccionado;
	private int numUdsArticulo;

	private List<Articulo> catalogo;

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Pedido getPedido() {
		return myPedido;
	}
	public void setMyPedido(Pedido pedido) {
		this.myPedido = pedido;
	}
	public List<Articulo> getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(List<Articulo> catalogo) {
		this.catalogo = catalogo;
	}
	public List<LineaPedido> carrito(){
		return this.myGestionPedidos.verCarroActual();
	}
	


	// Metodos 
	public String iniciarSesion() {
		this.myPedido = myGestionPedidos.iniciarPedido(dni);
		if (myPedido == null) {
			return pantallaRegistrarse;
		} 
		this.setCatalogo(myConsultaArticulos.articulos());
		return pantallaArticulos;
	}
	
	public String registrarse() {
		return pantallaRegistrarse;
	}
	
	public String anhadirACarro(long id) {
		articuloSeleccionado = myConsultaArticulos.buscaArticulo(id);
		List<LineaPedido> myCart = myGestionPedidos.anhadirArticuloACarrito(articuloSeleccionado.getId(), numUdsArticulo);
		if (myCart != null) {
			return pantallaCarro;
		} 
		return "noHayStock.xtml";
	}

	public String verCarro() {
		return pantallaCarro;
	}

	public String confirma() {
		Pedido ped = myGestionPedidos.confirmarCarro(LocalTime.parse(horaReco));
		if (ped == null) {
			return "errorHora.xtml";
		}
		myPedido = ped;
		return pantallaRealizado;
	}
	

}
