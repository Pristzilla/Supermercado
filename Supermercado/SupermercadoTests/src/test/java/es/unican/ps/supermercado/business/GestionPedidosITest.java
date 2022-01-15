/**
 *
 * Author: Sara Grela y Javier Barquin
 * Date: 3 dic. 2021
 *
 */
package es.unican.ps.supermercado.business;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercado.businessLayer.IGestionPedidosRemote;

import es.unican.ps.supermercado.entities.LineaPedido;

/**
 * @author barquinj
 *
 */
public class GestionPedidosITest {

	private static EJBContainer ec;
	private static IGestionPedidosRemote sut;
	String dniExistente = "72105220J";


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@BeforeClass
	public static void initContainer() throws Exception {
		Map properties = new HashMap();
		properties.put(EJBContainer.MODULES, new File[] {new File("clases")});

		// properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "<ruta a glassfish>");
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "/Users/barquinj/glassfish5/glassfish");

		// Se crea el EJBContainter con propiedades
		ec = EJBContainer.createEJBContainer(properties);

		//		INFORMACIÓN: Portable JNDI names for EJB GestionPedidos:
		sut = (IGestionPedidosRemote) ec.getContext().lookup("java:global/ejb-app/SupermercadoBusiness-0_0_1-SNAPSHOT/GestionPedidos!es.unican.ps.supermercado.businessLayer.IGestionPedidosRemote");
	}


	@AfterClass
	public static void closeContainer() throws Exception {
		if (ec != null) {
			ec.close();
		}
	}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Se supone que este metodo funciona correctamente ya que para anhadir al carrito
		// es necesario haber iniciado un pedido previamente.
		sut.iniciarPedido(dniExistente);
	}


	@Test
	public void testAnhadirArticuloACarrito() {
		List<LineaPedido> carritoReal = sut.verCarroActual();
		List<LineaPedido> carritoActual = new LinkedList<>();
		// UGIC. 1.c idArticulo existente y stock del mismo suficiente
		carritoActual = sut.anhadirArticuloACarrito(1L, 1);
		// se comprueba que el carro ahora tiene un articulo mas
		assertEquals(1, carritoActual.size());
		carritoReal = sut.verCarroActual();
		assertEquals(1,carritoReal.size());
		// se comprueba que se ha anhadido ese articulo con la cantidad indicada
		assertEquals("Agua de Solares 1L", carritoActual.get(carritoActual.size()-1).getArticulo().getNombre());
		assertEquals(1, carritoActual.get(carritoActual.size()-1).getCantidad());


		// UGIC. 1.d idArticulo existente y stock del mismo insuficiente.
		// Se comprueba que previamente no hay el articulo Manzana Golden en el carrito.
		for(LineaPedido lc : carritoReal) {
			assertNotEquals("Manzana Golden",lc.getArticulo().getNombre());
		}
		carritoActual = sut.anhadirArticuloACarrito(2L, 100);
		// Se comprueba que el metodo retorna null al no haber stock suficiente
		assertEquals(null, carritoActual);
		// se comprueba que no se ha anhadido el articulo al carrito ni sus unidades
		carritoReal = sut.verCarroActual();
		assertEquals(1, carritoReal.size());
		assertNotEquals("Manzana Golden", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(100, carritoReal.get(carritoReal.size()-1).getCantidad());

		// UGIC. 1.e idArticulo existente y se solicitan 0 uds.
		carritoActual = sut.anhadirArticuloACarrito(2L, 0);
		// Se comprueba que no se ha ahadido el articulo al carrito ni con 0 uds.
		assertEquals(1, carritoActual.size());
		carritoReal = sut.verCarroActual();
		assertEquals(1, carritoReal.size());
		assertNotEquals("Manzana Golden", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(0, carritoReal.get(carritoReal.size()-1).getCantidad());


		// UGIC. 1.f idArticulo NO existente y se solicitan 2 uds.
		carritoActual = sut.anhadirArticuloACarrito(0L, 2);
		assertEquals(null, carritoActual);
		// Se comprueba que el carrito real sigue estando con las mismas lineas y no se anhade un articulo que no existe 
		// ni las unidades solicitadas
		carritoReal = sut.verCarroActual();
		assertEquals(1, carritoReal.size());
		assertNotNull(carritoReal.get(carritoReal.size()-1).getArticulo());
		assertNotEquals(2, carritoReal.get(carritoReal.size()-1).getCantidad());

		// UGIC. 1.g idArticulo null y se solicitan 2 uds.
		carritoActual = sut.anhadirArticuloACarrito(null, 2);
		assertEquals(null, carritoActual);
		// Se comprueba que el carrito real sigue estando con las mismas lineas y no se anhade un articulo nulo 
		// ni las unidades solicitadas
		carritoReal = sut.verCarroActual();
		assertEquals(1, carritoReal.size());
		assertNotNull(carritoReal.get(carritoReal.size()-1).getArticulo());
		assertNotEquals(2, carritoReal.get(carritoReal.size()-1).getCantidad());
		
		// UGIC. 1.h idArticulo existente y stock del mismo insuficiente.

		carritoActual = sut.anhadirArticuloACarrito(2L, -1);
		// Se comprueba que el metodo retorna null al no haber stock suficiente
		assertEquals(null, carritoActual);
		// se comprueba que no se ha anhadido el articulo al carrito ni sus unidades
		carritoReal = sut.verCarroActual();
		assertEquals(1, carritoReal.size());
		assertNotEquals("Manzana Golden", carritoReal.get(carritoReal.size()-1).getArticulo().getNombre());
		assertNotEquals(-1, carritoReal.get(carritoReal.size()-1).getCantidad());

	}

}
