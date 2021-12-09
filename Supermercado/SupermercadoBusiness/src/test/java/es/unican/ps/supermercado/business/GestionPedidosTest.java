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

import es.unican.ps.supermercado.daoLayer.IArticulosDAO;
import es.unican.ps.supermercado.entities.Articulo;


/**
 * @authors Sara Grela and Javier Barquin
 *
 */
public class GestionPedidosTest {

	
	private GestionPedidos sut;
	
	@Mock
	private IArticulosDAO mockArticulos;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	private Articulo articuloExistente1, articuloExistente2;
	
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		// Asignacion del sut
		sut = new GestionPedidos();
		
		// Programacion de mocks
		articuloExistente1 = new Articulo(10, 5.99);
		articuloExistente1.setId(1L);
		
		articuloExistente2 = new Articulo(100, 2.50);
		articuloExistente2.setId(2L);
		
		when(mockArticulos.buscarArticuloPorId(0L)).thenReturn(null);
		when(mockArticulos.buscarArticuloPorId(1L)).thenReturn(articuloExistente1);
		when(mockArticulos.buscarArticuloPorId(2L)).thenReturn(articuloExistente2);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
