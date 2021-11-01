package es.unican.ps.supermercado.entities;

import java.time.LocalTime;
import java.util.List;
public class Supermercado {

	private Long id;
	private static LocalTime HORA_APERTURA = LocalTime.parse("09:00:00");
	private static LocalTime HORA_CIERRE = LocalTime.parse("21:00:00");
	private List<Usuario> usuarios;
	private List<Articulo> articulosDisponibles;
	
	/**
	 * Crea un supermercado
	 * @param horaApertura
	 * @param horaCierre
	 * @param usuarios
	 * @param articulosDisponibles
	 */
	public Supermercado(List<Usuario> usuarios, List<Articulo> articulosDisponibles) {
		this.usuarios = usuarios;
		this.articulosDisponibles = articulosDisponibles;
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
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the articulosDisponibles
	 */
	public List<Articulo> getArticulosDisponibles() {
		return articulosDisponibles;
	}

	/**
	 * @param articulosDisponibles the articulosDisponibles to set
	 */
	public void setArticulosDisponibles(List<Articulo> articulosDisponibles) {
		this.articulosDisponibles = articulosDisponibles;
	}
	
	
	
}
