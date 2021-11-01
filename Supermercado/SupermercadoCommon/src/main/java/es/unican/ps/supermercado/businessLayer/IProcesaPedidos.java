package es.unican.ps.supermercado.businessLayer;

import es.unican.ps.supermercado.entities.Pedido;
import es.unican.ps.supermercado.entities.Usuario;

public interface IProcesaPedidos {

	public boolean entregaPedido(Pedido p, Usuario u);
	public boolean procesarPedido(Pedido p);
}
