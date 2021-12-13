/**
 *
 * Authors: Sara Grela and Javier Barquin
 * Date: 2 dic. 2021
 *
 */
package es.unican.ps.supermercado.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.when;

import java.util.List;

import es.unican.ps.supermercado.daoLayer.IArticulosDAO;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.LineaPedido;
import es.unican.ps.supermercado.entities.Pedido;


/**
 * @authors Sara Grela and Javier Barquin
 *
 */
public class GestionPedidosTest {


	private GestionPedidos sut;

	@Mock
	private IArticulosDAO mockArticulosDAO;
	
	private Pedido pedido;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	private Articulo articuloExistente;



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {


		
		// Programacion de mocks
		pedido = new Pedido(Pedido.Estado.NO_CONFIRMADO);
		articuloExistente = new Articulo(10, 5.99);
		articuloExistente.setId(1L);


		when(mockArticulosDAO.buscarArticuloPorId(1L)).thenReturn(articuloExistente);
		when(mockArticulosDAO.buscarArticuloPorId(0L)).thenReturn(null);

		// Asignacion del sut
		sut = new GestionPedidos();
		sut.setArticulosDAO(mockArticulosDAO);
		sut.setPedido(pedido);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAnhadirArticuloACarrito() {

		List<LineaPedido> carrito = sut.verCarroActual();
		 assertEquals(0, carrito.size());
		 
		// UGIC. 1.c idArticulo existente y stock del mismo suficiente
		carrito = sut.anhadirArticuloACarrito(1L, 1);
		assertEquals(1, carrito.size());

		// UGIC. 1.d idArticulo existente y stock del mismo insuficiente
		carrito = sut.anhadirArticuloACarrito(1L, 100);
		assertEquals(null, carrito);

		// UGIC. 1.e idArticulo existente y se solicitan 0 uds.
		carrito = sut.anhadirArticuloACarrito(1L, 0);
		assertEquals(1, carrito.size());

		// UGIC. 1.f idArticulo NO existente y se solicitan 0 uds.
		carrito = sut.anhadirArticuloACarrito(0L, 1);
		assertEquals(null, carrito);

		// UGIC. 1.g idArticulo null y se solicitan 1 uds.
		carrito = sut.anhadirArticuloACarrito(null, 1);
		assertEquals(null, carrito);

	}

}
