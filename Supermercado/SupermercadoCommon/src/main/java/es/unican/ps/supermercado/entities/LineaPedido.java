package es.unican.ps.supermercado.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Linea_pedido")
public class LineaPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="Articulo_FK") // Se usa para cambiar de nombre la relacion
	private Articulo articulo;
	
	public LineaPedido() {}
	/**
	 * @param cantidad
	 * @param articulo
	 */
	public LineaPedido(int cantidad, Articulo articulo) {
		super();
		this.cantidad = cantidad;
		this.articulo = articulo;
	}
	
	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}


	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}


	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	
}
