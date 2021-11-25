package es.unican.ps.supermercado.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {
	
	@Id
	private Long id;
	private String nombre;
	private String dni;
	private String direccion;
	private String email;
	private int comprasMensuales;
	private List<Pedido> pedidos;
	
	/**
	 * Construye un usuario
	 * @param nombre
	 * @param dni
	 * @param direccion
	 * @param email
	 * @param comprasMensuales
	 * @param pedidos
	 */
	public Usuario(String nombre, String dni, String direccion, String email, int comprasMensuales,
			List<Pedido> pedidos) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.comprasMensuales = comprasMensuales;
		this.pedidos = pedidos;
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
		this.nombre = nombre;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the comprasMensuales
	 */
	public int getComprasMensuales() {
		return comprasMensuales;
	}

	/**
	 * @param comprasMensuales the comprasMensuales to set
	 */
	public void setComprasMensuales(int comprasMensuales) {
		this.comprasMensuales = comprasMensuales;
	}
	
	/**
	 * Anhade una nueva compra
	 */
	public void addCompraMensual() {
		this.comprasMensuales++;
	}

	/**
	 * @return the pedidos
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	/**
	 * @param pedidos the pedidos to set
	 */
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Usuario) {
			Usuario u = (Usuario) o;
			return u.getDni().equals(dni);
		}
		return false;
	}
	
	

}
