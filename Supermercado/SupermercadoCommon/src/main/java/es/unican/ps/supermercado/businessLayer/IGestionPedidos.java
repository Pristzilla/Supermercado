package es.unican.ps.supermercado.businessLayer;

import java.time.LocalTime;

import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.Pedido;

public interface IGestionPedidos {
	
	public Pedido realizarPedido(Pedido p, String dni);
	public boolean anhadirArticuloACarrito(Pedido p, Articulo a, int uds);
	public boolean confirmarCarro(Pedido p, LocalTime horaRegogida);

}
