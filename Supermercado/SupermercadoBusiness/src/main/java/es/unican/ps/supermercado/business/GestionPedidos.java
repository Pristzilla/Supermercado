package es.unican.ps.supermercado.business;

import javax.ejb.Stateful;
import java.time.LocalTime;
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
	private Supermercado supermercado = new Supermercado(null, null); // TODO se hace asi?
	
	public Pedido realizarPedido(Pedido p, String dni) {
		if (usuariosDAO.buscarUsuarioPorDNI(dni) == null) {
			return null;
		}
		// TODO
		return p;
		
	}
	
	public boolean anhadirArticuloACarrito(Pedido p, Articulo a, int uds) {
		if (a.getUnidadesStock() < uds) {
			return false;
		}
		// Se anhade el articulo al pedido
		LineaPedido linea = new LineaPedido(uds, a);
		p.addLineaPedido(linea);
		// Se actualiza el stock
		a.setUnidadesStock(a.getUnidadesStock() - uds);
		// TODO mostrar estado pedido?
		return true;
		
	}
	
	public boolean confirmarCarro(Pedido p, LocalTime horaRegogida) {
		if (horaRegogida.isAfter(supermercado.getHoraApertura()) 
				&& horaRegogida.isBefore(supermercado.getHoraCierre())) {
			p.setHoraRecogida(horaRegogida);
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
