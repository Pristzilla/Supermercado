package es.unican.ps.supermercado.business;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
	private IUsuariosDAOLocal usuariosDAO;
	@EJB
	private IPedidosDAOLocal pedidosDAO;
	@EJB
	private IArticulosDAOLocal articulosDAO;

	private Pedido pedido;
	private Usuario usuario;
	private Supermercado supermercado = new Supermercado(); // TODO coger de BBDD

	@Override
	public Pedido iniciarPedido(String dni) {
		Usuario u = usuariosDAO.buscarUsuarioPorDNI(dni);
		if (u == null) {
			this.usuario = null;
			return null;
		}
		this.usuario = u;
		this.pedido = new Pedido(Pedido.Estado.NO_CONFIRMADO);
		this.pedido.setUsuario(u);
		return this.pedido;
	}

	@Override
	public List<LineaPedido> anhadirArticuloACarrito(Articulo a, int uds) {
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
	public boolean confirmarCarro(LocalTime horaRegogida) {
		if (horaRegogida.isAfter(supermercado.getHoraApertura()) 
				&& horaRegogida.isBefore(supermercado.getHoraCierre())) {
			this.pedido.setHoraRecogida(horaRegogida);
			Articulo art;
			for (LineaPedido linea: this.pedido.getLineasPedido()) {
				art = linea.getArticulo();
				art.setUnidadesStock(art.getUnidadesStock() - linea.getCantidad());
				articulosDAO.modificarArticulo(art);
			}
			return true;
		}
		return false;

	}

	/**
	 * Metodo que almacena el pedido cuando se confirma el carro
	 * @param p pedido a almacenar
	 * @return Pedido el pedido almacenado
	 *  @return null si no se ha podido almacenar el pedido
	 */
	@Override
	public Pedido almacenaPedido() {
		String refPedido = this.pedido.getUsuario().getDni() + LocalDateTime.now().toString();
		this.pedido.setRef(refPedido);
		this.pedido.setEstado(Pedido.Estado.PENDIENTE);
		this.pedido = aplicarDescuento();
		this.pedido.setFecha(LocalDateTime.now()); // la fecha del pedido se actualiza cuando se confirma el carro
		this.pedido = pedidosDAO.crearPedido(this.pedido);		
		return this.pedido;

	}

	/**
	 * Metodo que aplica el descuento a un usuario si este lleva mas de 
	 * 10 compras realizadas el mismo mes.
	 */

	private Pedido aplicarDescuento() {
		Pedido p = null;
		if (this.usuario.getComprasMensuales() > NUM_COMPRAS_MENSUALES) {
			this.pedido.aplicarDescuento(VALOR_DESCUENTO);
			p = pedidosDAO.modificarPedido(this.pedido);

		}
		return p;
	}

	@Override
	public Pedido entregaPedido(String ref, String dni) {

		Pedido p = pedidosDAO.buscarPedidoPorReferencia(ref);
		Usuario u = usuariosDAO.buscarUsuarioPorDNI(dni);
		if (p == null || u == null || !(p.getUsuario().equals(u))) 
			return null;
		p.setEstado(Pedido.Estado.ENTREGADO);
		u.addCompraMensual();
		usuariosDAO.modificarUsuario(u);
		p = pedidosDAO.modificarPedido(p);
		return p;
	}

	/**
	 * Metodo que procesa el primer pedido pendiente del supermercado
	 * @return Pedido p si se ha procesado el pedido pendiente
	 * 			null si no hay pedidos pendientes
	 */
	@Override
	public Pedido procesarPedido() {
		Pedido p = pedidosDAO.buscarPrimerPedidoPendiente();
		if(p != null) {
			p.setEstado(Pedido.Estado.PROCESADO);
			p = pedidosDAO.modificarPedido(p);
			return p;
		}
		return null;

	}

}
