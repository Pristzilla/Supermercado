package es.unican.ps.supermercado.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Pedido {
	
	private String ref;
	private Long id;
	private Estado estado;
	private LocalDateTime fecha;
	private LocalTime horaRecogida;
	private List<LineaPedido> lineasPedido;
	private Usuario usuario;

	public enum Estado {
		PREPARANDO, ENTREGADO, DISPONIBLE
	}
	
	public Pedido(String ref, Estado estado, LocalDateTime fecha, LocalTime horaRecogida) {
		this.ref = ref;
		this.estado = estado;
		this.fecha = fecha;
		this.horaRecogida = horaRecogida;
	}
	
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
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
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the horaRecogida
	 */
	public LocalTime getHoraRecogida() {
		return horaRecogida;
	}

	/**
	 * @param horaRecogida the horaRecogida to set
	 */
	public void setHoraRecogida(LocalTime horaRecogida) {
		this.horaRecogida = horaRecogida;
	}

	/**
	 * @return the lineasPedido
	 */
	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	/**
	 * @param lineasPedido the lineasPedido to set
	 */
	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
