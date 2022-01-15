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

import java.util.Collections;
import java.util.LinkedList;
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

		articuloSinStock = new Articulo (20, 1.95);
		articuloSinStock.setId(2L);
		articuloSinStock.setNombre("Coca-Cola 33cl");

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
		pedido.setLineasPedido(Collections.emptyList());
		sut.setPedido(pedido);
	}

	@Test
	public void testAnhadirArticuloACarrito() {

		List<LineaPedido> carritoReal = sut.verCarroActual();
		List<LineaPedido> carritoActual = new LinkedList<>();
		// UGIC. 1.c idArticulo existente y stock del mismo suficiente
		carritoActual = sut.anhadirArticuloACarrito(1L, 1);
		// se comprueba que el carro ahora tiene un articulo mas
		assertEquals(1, carritoActual.size());
		// se comprueba que se ha anhadido ese articulo con la cantidad indicada
		assertEquals("Agua de Solares 1L", carritoActual.get(carritoActual.size()-1).getArticulo().getNombre());
		assertEquals(1, carritoActual.get(carritoActual.size()-1).getCantidad());


		// UGIC. 1.d idArticulo existente y stock del mismo insuficiente.
		// Se comprueba que previamente no hay el articulo Coca-Cola en el carrito.
		for(LineaPedido lc : carritoReal) {
			assertNotEquals("Coca-Cola 33cl",lc.getArticulo().getNombre());
		}
		carritoActual = sut.anhadirArticuloACarrito(2L, 100);
		// Se comprueba que el metodo retorna null al no haber stock suficiente
		assertEquals(null, carritoActual);
		// se comprueba que no se ha anhadido el articulo al carrito ni sus unidades
		assertEquals(1, carritoReal.size());
		assertNotEquals("Coca-Cola 33cl", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(100, carritoReal.get(carritoReal.size()-1).getCantidad());

		// UGIC. 1.e idArticulo existente y se solicitan 0 uds.
		carritoActual = sut.anhadirArticuloACarrito(2L, 0);
		// Se comprueba que no se ha ahadido el articulo al carrito ni con 0 uds.
		assertEquals(1, carritoReal.size());
		assertEquals(1, carritoActual.size());
		assertNotEquals("Coca-Cola 33cl", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(0, carritoReal.get(carritoReal.size()-1).getCantidad());


		// UGIC. 1.f idArticulo NO existente y se solicitan 2 uds.
		carritoActual = sut.anhadirArticuloACarrito(0L, 2);
		assertEquals(null, carritoActual);
		// Se comprueba que el carrito real sigue estando con las mismas lineas y no se anhade un articulo que no existe 
		// ni las unidades solicitadas
		assertEquals(1, carritoReal.size());
		assertNotNull(carritoReal.get(carritoReal.size()-1).getArticulo());
		assertNotEquals(2, carritoReal.get(carritoReal.size()-1).getCantidad());

		// UGIC. 1.g idArticulo null y se solicitan 2 uds.
		carritoActual = sut.anhadirArticuloACarrito(null, 2);
		assertEquals(null, carritoActual);
		// Se comprueba que el carrito real sigue estando con las mismas lineas y no se anhade un articulo nulo 
		// ni las unidades solicitadas
		assertEquals(1, carritoReal.size());
		assertNotNull(carritoReal.get(carritoReal.size()-1).getArticulo());
		assertNotEquals(2, carritoReal.get(carritoReal.size()-1).getCantidad());

		// UGIC. 1.h idArticulo existente y udsArticulo valor negativo.
		carritoActual = sut.anhadirArticuloACarrito(2L, -1);
		// Se comprueba que el metodo retorna null al no haber stock suficiente
		assertEquals(null, carritoActual);
		// se comprueba que no se ha anhadido el articulo al carrito ni sus unidades
		assertEquals(1, carritoReal.size());
		assertNotEquals("Coca-Cola 33cl", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(-1, carritoReal.get(carritoReal.size()-1).getCantidad());

	}

}
