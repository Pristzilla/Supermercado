package es.unican.ps.supermercado.business;


import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import es.unican.ps.supermercado.businessLayer.*;
import es.unican.ps.supermercado.daoLayer.*;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.LineaPedido;
import es.unican.ps.supermercado.entities.Pedido;
import es.unican.ps.supermercado.entities.Supermercado;
import es.unican.ps.supermercado.entities.Usuario;

@Stateful
public class GestionPedidos implements IGestionPedidosLocal, IGestionPedidosRemote,
IProcesaPedidosLocal, IProcesaPedidosRemote {

	private static final int NUM_COMPRAS_MENSUALES = 10;
	private static final int VALOR_DESCUENTO = 5;

	@EJB
	private IUsuariosDAORemote usuariosDAO;
	@EJB
	private IPedidosDAORemote pedidosDAO;
	@EJB
	private IArticulosDAORemote articulosDAO;

	@Resource 
	private SessionContext context;
	
	private Pedido pedido;
	private Usuario usuario;
	private Supermercado supermercado = new Supermercado(); 

	@Override
	public Pedido iniciarPedido(String dni) {
		Usuario u = usuariosDAO.buscarUsuarioPorDNI(dni);
		if (u == null) {
			this.usuario = null;
			return null;
		}
		this.usuario = u;
		this.setPedido(new Pedido(Pedido.Estado.NO_CONFIRMADO));
		this.pedido.setUsuario(u);
		this.pedido.setDescuento(this.calculaDescuento());
		return this.pedido;
	}

	@Override
	public List<LineaPedido> anhadirArticuloACarrito(Long idArt, int uds) {
		if (idArt == null) {
			return null;
		}
		Articulo a = articulosDAO.buscarArticuloPorId(idArt);
		if (a == null || a.getUnidadesStock() < uds) {
			return null;
		}
		if (uds > 0) {
			// Se anhade el articulo al pedido
			LineaPedido linea = new LineaPedido(uds, a);
			this.pedido.addLineaPedido(linea);
			
		}
		
		return this.pedido.getLineasPedido();
	}

	@Override
	public Pedido confirmarCarro(LocalTime horaRegogida) {
		if (horaRegogida.isAfter(supermercado.getHoraApertura()) 
				&& horaRegogida.isBefore(supermercado.getHoraCierre())) {
			this.pedido.setHoraRecogida(horaRegogida);
			Articulo art;
			for (LineaPedido linea: this.pedido.getLineasPedido()) {
				art = linea.getArticulo();
				art.setUnidadesStock(art.getUnidadesStock() - linea.getCantidad());
				articulosDAO.modificarArticulo(art);
			}
			String refPedido = this.pedido.getUsuario().getDni() + LocalDateTime.now().toString();
			this.pedido.setRef(refPedido);
			this.pedido.setEstado(Pedido.Estado.PENDIENTE);
			this.pedido.calculaTotalPedido();
			this.pedido.setFecha(LocalDateTime.now()); // la fecha del pedido se actualiza cuando se confirma el carro
			this.setPedido(pedidosDAO.crearPedido(this.pedido));	
			this.usuario.addCompraMensual();
			usuariosDAO.modificarUsuario(this.usuario);
			return this.pedido;
		}
		return null;
	}

	
	@Remove
	public void cerrarSesion() {
		// Cierra la sesion del Stateful.
	}
	/**
	 * Metodo que aplica el descuento a un usuario si este lleva mas de 
	 * 10 compras realizadas el mismo mes.
	 */
	
	private int calculaDescuento() { 
		int descuento = 0;
		if (this.usuario.getComprasMensuales() > NUM_COMPRAS_MENSUALES) {
			descuento = VALOR_DESCUENTO;
		}
		return descuento;
	}
	
	@Override
	public Pedido entregaPedido(String ref, String dni) {

		Pedido p = pedidosDAO.buscarPedidoPorReferencia(ref);
		Usuario u = usuariosDAO.buscarUsuarioPorDNI(dni);
		if (p == null || u == null || !(p.getUsuario().equals(u))) 
			return null;
		p.setEstado(Pedido.Estado.ENTREGADO);
		p = pedidosDAO.modificarPedido(p);
		return p;
	}

	
	private Pedido buscarPrimerPedidoPendiente() {
		List<Pedido> pedidos = pedidosDAO.pedidos();
		Collections.sort(pedidos);
		for(Pedido p: pedidos) {
			if(p.getEstado() == Pedido.Estado.PENDIENTE) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que procesa el primer pedido pendiente del supermercado
	 * @return Pedido p si se ha procesado el pedido pendiente
	 * 			null si no hay pedidos pendientes
	 */
	@Override
	public Pedido procesarPedido() {
		Pedido p = buscarPrimerPedidoPendiente();
		if(p != null) {
			p.setEstado(Pedido.Estado.PROCESADO);
			p = pedidosDAO.modificarPedido(p);
			return p;
		}
		return null;

	}
	
	
	public List<LineaPedido> verCarroActual(){
		return this.pedido.getLineasPedido();
	}

	/**
	 * @param articulosDAO the articulosDAO to set
	 */
	public void setArticulosDAO(IArticulosDAORemote articulosDAO) {
		this.articulosDAO = articulosDAO;
	}

	/**
	 * @param pedido the pedido to set PARA TESTS
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	
	

}
