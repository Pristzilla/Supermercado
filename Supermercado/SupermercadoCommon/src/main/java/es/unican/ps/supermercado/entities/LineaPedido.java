package es.unican.ps.supermercado.entities;

public class LineaPedido {

	private int cantidad;
	private Long id;
	private Articulo articulo;
	
	
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


	/**
	 * @param cantidad
	 * @param articulo
	 */
	public LineaPedido(int cantidad, Articulo articulo) {
		super();
		this.cantidad = cantidad;
		this.articulo = articulo;
	}
}
