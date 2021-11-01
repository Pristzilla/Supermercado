package es.unican.ps.supermercado.business;

import java.time.LocalDateTime;
import es.unican.ps.supermercado.businessLayer.*;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.Pedido;
import es.unican.ps.supermercado.entities.Usuario;

public class GestionPedidos implements IGestionPedidosLocal, IGestionPedidosRemote,
IProcesaPedidosLocal, IProcesaPedidosRemote {
	
	public Pedido realizarPedido(Pedido p, String dni) {
		return p;
		
	}
	public boolean anhadirArticuloACarrito(Articulo a, int uds) {
		return false;
		
	}
	public boolean confirmarCarro(LocalDateTime horaRegogida) {
		return false;
		
	}
	public boolean entregaPedido(Pedido p, Usuario u) {
		return false;
		
	}
	public boolean procesarPedido(Pedido p) {
		return false;
		
	}

}
