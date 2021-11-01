package es.unican.ps.supermercado.businessLayer;

import es.unican.ps.supermercado.entities.Pedido;

public interface IProcesaPedidos {

	public Pedido entregaPedido(String ref, String dni);
	public Pedido procesarPedido(Pedido p);
}
