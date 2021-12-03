package es.unican.ps.supermercado.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Articulos")
public class Articulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private static String nombre;
	@Column(name="uds_stock")
	private int unidadesStock;
	private double precio;
	
	public Articulo() {}
	public Articulo(int unidadesStock, double precio) {
		this.unidadesStock = unidadesStock;
		this.precio = precio;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Articulo.nombre = nombre;
	}

	/**
	 * @return the unidadesStock
	 */
	public int getUnidadesStock() {
		return unidadesStock;
	}

	/**
	 * @param unidadesStock the unidadesStock to set
	 */
	public void setUnidadesStock(int unidadesStock) {
		this.unidadesStock = unidadesStock;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Articulo) {
			Articulo a = (Articulo) o;
			return a.getNombre().equals(nombre) && a.getPrecio() == precio;
		}
		return false;
	}
}
