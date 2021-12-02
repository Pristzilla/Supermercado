package es.unican.ps.supermercado.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Pedidos")
public class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String ref;
	private Estado estado;
	private LocalDateTime fecha;
	@Column(name="hora_recogida")
	private LocalTime horaRecogida;

	@OneToMany
	@JoinColumn(name="Pedido_FK")
	private List<LineaPedido> lineasPedido;
	@ManyToOne
	@JoinColumn(name="Usuario_FK")
	private Usuario usuario;
	@Transient
	private int descuento = 0;
	private double precioTotal = 0.00;

	public enum Estado {
		NO_CONFIRMADO, PENDIENTE, PROCESADO, ENTREGADO
	}
	
	public Pedido(Estado estado) {
		this.estado = estado;
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
	
	public void addLineaPedido(LineaPedido linea) {
		this.lineasPedido.add(linea);
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
	
	/**
	 * Aplica un descuento al pedido
	 * @param descuento descuento a aplicar
	 */
	public void aplicarDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	/**
	 * calcula el precio total del pedido
	 */
	public void calculaTotalPedido() {
		double subtotal = 0.0;
		for (LineaPedido linea : lineasPedido) {
			subtotal = linea.getArticulo().getPrecio();
		}
		this.precioTotal = subtotal*(100-this.descuento);
	}
	
	/**
	 * @return precioTotal
	 */
	public double getPrecioTotal() {
		return this.precioTotal;
	}
	
	/**
	 * 
	 * @param precioTotal, precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	/**
	 * @return String, la hora de recogida en string
	 */
	public String getHoraRecogidaString() {
		return horaRecogida.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
	
}
