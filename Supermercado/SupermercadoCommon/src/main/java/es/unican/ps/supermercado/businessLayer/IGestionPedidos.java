package es.unican.ps.supermercado.businessLayer;

import java.time.LocalDateTime;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.Pedido;

public interface IGestionPedidos {
	
	public Pedido realizarPedido(Pedido p, String dni);
	public boolean anhadirArticuloACarrito(Articulo a, int uds);
	public boolean confirmarCarro(LocalDateTime horaRegogida);

}
