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

import es.unican.ps.supermercado.daoLayer.IArticulosDAORemote;
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
	private IArticulosDAORemote mockArticulosDAO;
	
	private Pedido pedido;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	private Articulo articuloExistente, articuloSinStock;



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {


		
		// Programacion de mocks
		pedido = new Pedido(Pedido.Estado.NO_CONFIRMADO);
		articuloExistente = new Articulo(10, 5.99);
		articuloExistente.setId(1L);
		articuloExistente.setNombre("Agua de Solares 1L");

		articuloSinStock = new Articulo (1, 1.95);
		articuloSinStock.setId(2L);
		articuloExistente.setNombre("Coca-Cola 33cl");

		when(mockArticulosDAO.buscarArticuloPorId(1L)).thenReturn(articuloExistente);
		when(mockArticulosDAO.buscarArticuloPorId(0L)).thenReturn(null);
		when(mockArticulosDAO.buscarArticuloPorId(2L)).thenReturn(articuloSinStock);


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

		List<LineaPedido> carritoPrevio = sut.verCarroActual();
		List<LineaPedido> carrito = carritoPrevio;
		// UGIC. 1.c idArticulo existente y stock del mismo suficiente
		carrito = sut.anhadirArticuloACarrito(1L, 1);
		// se comprueba que el carro ahora tiene un articulo mas
		assertEquals(carritoPrevio.size()+1, carrito.size());
		// se comprueba que se ha anhadido ese articulo
		assertEquals("Agua de Solares 1L", carrito.get(carrito.size()-1).getArticulo().getNombre());
		assertEquals(1, carrito.get(carrito.size()-1).getCantidad());

		// Se comprueba que previamente no hay el articulo Coca-Cola en el carrito.
		for(LineaPedido lc : carritoPrevio) {
			assertNotEquals("Coca-Cola 33cl",lc.getArticulo().getNombre());
		}		
		// UGIC. 1.d idArticulo existente y stock del mismo insuficiente.
		carrito = sut.anhadirArticuloACarrito(2L, 100);
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
