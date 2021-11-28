package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import es.unican.ps.supermercado.entities.Pedido;

public interface IPedidosDAO {

	public Pedido crearPedido (Pedido p);
	public Pedido modificarPedido (Pedido p);
	public Pedido eliminarPedido (Pedido p);
	public Pedido buscarPedidoPorId (Long id);
	public Pedido buscarPedidoPorReferencia (String ref);
	boolean anhadePedidoPendiente(Pedido p);
	Pedido procesarPendiente();
	public List<Pedido> pedidos();
}
