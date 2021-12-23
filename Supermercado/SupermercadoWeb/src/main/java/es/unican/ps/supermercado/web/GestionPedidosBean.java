/**
 *
 * Author: Javier Barquin
 * Date: 16 dic. 2021
 *
 */
package es.unican.ps.supermercado.web;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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
public class GestionPedidosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String pantallaSeleccionado = "articuloSeleccionado";
	
	private String horaReco;
	private String dni;
	private Pedido pedido;
	private Articulo articuloSeleccionado;
	private int udsArticulo;
	private List<LineaPedido> carrito;

	private List<Articulo> catalogo;
	
	public GestionPedidosBean() {
		
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getUdsArticulo() {
		return udsArticulo;
	}

	public void setUdsArticulo(int udsArticulo) {
		this.udsArticulo = udsArticulo;
	}

	public String getHoraReco() {
		return horaReco;
	}

	public void setHoraReco(String horaReco) {
		this.horaReco = horaReco;
	}
	public Articulo getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(Articulo articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public List<LineaPedido> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<LineaPedido> carrito) {
		this.carrito = carrito;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Articulo> getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(List<Articulo> catalogo) {
		this.catalogo = catalogo;
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
	public String seleccionarArticulo(long id) {
		articuloSeleccionado = consultaArticulos.buscaArticulo(id);
		return pantallaSeleccionado;
	}
	public String anhadirACarro() {
		List<LineaPedido> carro = gestionPedidos.anhadirArticuloACarrito(articuloSeleccionado.getId(), udsArticulo);
		if (carro != null) {
			setCarrito(carro);
			return pantallaCarro;
		} 
		return "noHayStock.xtml";
	}

	public String verCarro() {
		setCarrito(this.gestionPedidos.verCarroActual());
		return pantallaCarro;
	}

	public String confirmarCarro() {
		Pedido ped = gestionPedidos.confirmarCarro(LocalTime.parse(horaReco));
		if (ped == null) {
			return "errorHora";
		}
		pedido = ped;
		return pantallaRealizado;
	}
	
	public String cancelarPedido() {
		return pantallaInicio;
	}

	

}
