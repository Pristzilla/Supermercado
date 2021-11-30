package es.unican.ps.supermercado.businessLayer;

import java.time.LocalTime;
import java.util.List;

import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.LineaPedido;
import es.unican.ps.supermercado.entities.Pedido;

public interface IGestionPedidos {
	
	public Pedido iniciarPedido(String dni);
	public List<LineaPedido> anhadirArticuloACarrito( Long idArt, int uds);
	public boolean confirmarCarro(LocalTime horaRegogida);
	public Pedido almacenaPedido();

}
