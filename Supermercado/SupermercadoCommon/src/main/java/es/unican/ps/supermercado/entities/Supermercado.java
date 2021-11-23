package es.unican.ps.supermercado.entities;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
public class Supermercado {

	private Long id;
	private static LocalTime HORA_APERTURA = LocalTime.parse("09:00:00");
	private static LocalTime HORA_CIERRE = LocalTime.parse("21:00:00");	
	
	/**
	 * Crea un supermercado
	 * @param horaApertura
	 * @param horaCierre
	 * @param usuarios
	 * @param articulosDisponibles
	 */
	public Supermercado() {
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
	
}
