package es.unican.ps.supermercado.entities;

public class Articulo {

	private Long id;
	private static String nombre;
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
	public static String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public static void setNombre(String nombre) {
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
	
	
}
