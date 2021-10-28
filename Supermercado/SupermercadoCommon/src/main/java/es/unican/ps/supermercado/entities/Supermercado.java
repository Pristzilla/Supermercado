package es.unican.ps.supermercado.entities;

import java.time.LocalTime;
import java.util.List;
public class Supermercado {

	private Long id;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private List<Usuario> usuarios;
	private List<Articulo> articulosDisponibles;
	
	/**
	 * Crea un supermercado
	 * @param horaApertura
	 * @param horaCierre
	 * @param usuarios
	 * @param articulosDisponibles
	 */
	public Supermercado(LocalTime horaApertura, LocalTime horaCierre, List<Usuario> usuarios,
			List<Articulo> articulosDisponibles) {
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
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
		return horaApertura;
	}

	/**
	 * @param horaApertura the horaApertura to set
	 */
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * @return the horaCierre
	 */
	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	/**
	 * @param horaCierre the horaCierre to set
	 */
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
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
