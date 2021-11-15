package es.unican.ps.supermercado.entities;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
public class Supermercado {

	private Long id;
	private static LocalTime HORA_APERTURA = LocalTime.parse("09:00:00");
	private static LocalTime HORA_CIERRE = LocalTime.parse("21:00:00");	
	private Queue <Pedido> pedidosPendientes; // Cola con los pedidos pendientes
	
	/**
	 * Crea un supermercado
	 * @param horaApertura
	 * @param horaCierre
	 * @param usuarios
	 * @param articulosDisponibles
	 */
	public Supermercado() {
		this.pedidosPendientes = new LinkedList<Pedido>();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the horaApertura
	 */
	public LocalTime getHoraApertura() {
		return HORA_APERTURA;
	}

	/**
	 * @return the horaCierre
	 */
	public LocalTime getHoraCierre() {
		return HORA_CIERRE;
	}	
	
	/**
	 * Añade un pedido a la lista de pedidos pendientes del supermercado
	 * @param pedido el pedido a añadir.
	 * @return true si el pedido se ha añadido correctamente.
	 * 			false si no se ha podido añadir.
	 */
	public boolean anhadePedidoPendiente(Pedido pedido) {
		return pedidosPendientes.add(pedido);
	}
	
	/**
	 * Retorna un pedido pendiente para que el dependiente procese el pedido
	 * @return Pedido un pedido que este pendiente.
	 * 			null si no hay ningun pedido pendiente.
	 */
	public Pedido procesaPedidoPendiente() {
		return this.pedidosPendientes.poll();
	}
	
	
	
	
}
