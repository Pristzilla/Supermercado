package es.unican.ps.supermercado.business;

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
	
	private IUsuariosDAOLocal usuariosDAO;
	private IPedidosDAOLocal pedidosDAO;
	private Pedido pedido;
	private Supermercado supermercado = new Supermercado(); // TODO coger de BBDD
	
	public Pedido iniciarPedido(String dni) {
		if (usuariosDAO.buscarUsuarioPorDNI(dni) == null) {
			return null;
		}
		
		this.pedido = new Pedido(dni+LocalDateTime.now().toString(), Pedido.Estado.PREPARANDO, LocalDateTime.now());
		return this.pedido;
		
	}
	
	public List<LineaPedido> anhadirArticuloACarrito(Articulo a, int uds) {
		if (a.getUnidadesStock() < uds) {
			return null;
		}
		// Se anhade el articulo al pedido
		LineaPedido linea = new LineaPedido(uds, a);
		this.pedido.addLineaPedido(linea);
		// Se actualiza el stock
		a.setUnidadesStock(a.getUnidadesStock() - uds);
		return this.pedido.getLineasPedido();
		
	}
	
	public boolean confirmarCarro(LocalTime horaRegogida) {
		if (horaRegogida.isAfter(supermercado.getHoraApertura()) 
				&& horaRegogida.isBefore(supermercado.getHoraCierre())) {
			this.pedido.setHoraRecogida(horaRegogida);
			// TODO aplicar descuento usuario?
			return true;
		}
		return false;
		
	}
	public Pedido entregaPedido(String ref, String dni) {
		// TODO revisar
		Pedido p = pedidosDAO.buscarPedidoPorReferencia(ref);
		Usuario u = usuariosDAO.buscarUsuarioPorDNI(dni);
		if (p == null || u == null || !(p.getUsuario().equals(u)) ) 
			return null;
		p.setEstado(Pedido.Estado.ENTREGADO);
		u.addCompraMensual();
		return p;
	}
	
	
	public Pedido procesarPedido(Pedido p) {
		// TODO primer pedido pendiente?
		return null;
		
	}

}
