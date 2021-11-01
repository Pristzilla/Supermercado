package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import es.unican.ps.supermercado.entities.Pedido;

public interface IPedidosDAO {

	public Pedido crearPedido (Pedido p);
	public Pedido modificarPedido (Pedido p);
	public Pedido eliminarPedido (Pedido p);
<<<<<<< .merge_file_fqee7p
	public Pedido buscarPedidoPorID (Long id);
=======
	public Pedido buscarPedidoPorId (Long id);
>>>>>>> .merge_file_TCcVNu
	public Pedido buscarPedidoPorReferencia (String ref);

	public List<Pedido> Pedidos();
}
