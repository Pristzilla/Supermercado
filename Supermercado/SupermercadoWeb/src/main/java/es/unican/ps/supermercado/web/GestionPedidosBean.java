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
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class GestionPedidosBean {

	@EJB
	private IGestionPedidosRemote gestionPedidos;
	@EJB
	private IConsultaArticulosRemote consultaArticulos;
	// pantallas
	private String pantallaArticulos = "articulos";
	private String pantallaRegistrarse = "registrarse";
	private String pantallaCarro = "carroCompra";
	private String pantallaRealizado = "pedidoRealizado";
	private String pantallaInicio = "miSupermercado";
	
	private String horaReco;
	private String dni;
	private Pedido pedido;
	private Articulo articuloSeleccionado;
	private int udsArticulo;

	private List<Articulo> catalogo;
	
	public GestionPedidosBean() {
		
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setMyPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Articulo> getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(List<Articulo> catalogo) {
		this.catalogo = catalogo;
	}
	public List<LineaPedido> carrito(){
		return this.gestionPedidos.verCarroActual();
	}

	// Metodos 
	public String iniciarSesion() {
		this.pedido = gestionPedidos.iniciarPedido(dni);
		if (pedido == null) {
			return pantallaRegistrarse;
		} 
		this.setCatalogo(consultaArticulos.articulos());
		return pantallaArticulos;
	}
	
	public String registrarse() {
		return pantallaRegistrarse;
	}
	
	public String anhadirACarro(long id) {
		articuloSeleccionado = consultaArticulos.buscaArticulo(id);
		List<LineaPedido> carro = gestionPedidos.anhadirArticuloACarrito(articuloSeleccionado.getId(), udsArticulo);
		if (carro != null) {
			return pantallaCarro;
		} 
		return "noHayStock.xtml";
	}

	public String verCarro() {
		return pantallaCarro;
	}

	public String confirmarCarro() {
		Pedido ped = gestionPedidos.confirmarCarro(LocalTime.parse(horaReco));
		if (ped == null) {
			return "errorHora.xtml";
		}
		pedido = ped;
		return pantallaRealizado;
	}
	
	public String cancelarPedido() {
		return pantallaInicio;
	}

}
