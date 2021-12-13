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
import java.util.List;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercado.businessLayer.IGestionPedidosRemote;
import es.unican.ps.supermercado.entities.Articulo;
import es.unican.ps.supermercado.entities.LineaPedido;

/**
 * @author barquinj
 *
 */
public class GestionPedidosITest {

	private static EJBContainer ec;
	private static IGestionPedidosRemote sut;
	private static Articulo articuloExistente;
	private static long idExiste = 1L;
	private static long idNOExiste = 0L;


	//@SuppressWarnings({ "rawtypes", "unchecked" })
	@BeforeClass
	public static void initContainer() throws Exception {
		Map properties = new HashMap();
		properties.put(EJBContainer.MODULES, new File[] {new File("clases")});

		// properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "<ruta a glassfish>");
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "/Users/barquinj/glassfish5/glassfish");

		// Se crea el EJBContainter con propiedades
		ec = EJBContainer.createEJBContainer(properties);
//		ec.getContext().lookup("java:global/p<nombreEAR>/<nombreEJB>!myBeans.<Interfaz>"
//		sut = (GestionPedidos) ec.getContext().lookup("java:global/SupermercadoEAR/es.unican.ps-SupermercadoBusiness-0.0.1-SNAPSHOT/GestionPedidos"
//				+ "!"
//				+ "es.unican.ps.businessLayer.IGestionPedidosRemote");
		sut = (GestionPedidos) ec.getContext().lookup("java:comp/env/es.unican.ps.supermercado.business.GestionPedidos/usuariosDAO");
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
		articuloExistente = new Articulo(10, 5.99);
		articuloExistente.setId(idExiste);
		String dni = "72105220J";
//		sut.iniciarPedido(dni);
	}


	@Test
	public void testAnhadirArticuloACarrito() {
//		List<LineaPedido> carrito = sut.verCarroActual();
//		assertEquals(0, carrito.size());
//		 
//		// IGIC. 1.c idArticulo existente y stock del mismo suficiente
//		carrito = sut.anhadirArticuloACarrito(idExiste, 1);
//		assertEquals( 1, carrito.size());
//
//		// UGIC. 1.d idArticulo existente y stock del mismo insuficiente
//		carrito = sut.anhadirArticuloACarrito(idExiste, 100);
//		assertEquals(null, carrito);
//
//		// UGIC. 1.e idArticulo existente y se solicitan 0 uds.
//		carrito = sut.anhadirArticuloACarrito(idExiste, 0);
//		assertEquals(1, carrito.size());
//
//		// UGIC. 1.f idArticulo NO existente y se solicitan 0 uds.
//		carrito = sut.anhadirArticuloACarrito(idNOExiste, 1);
//		assertEquals(null, carrito);
//
//		// UGIC. 1.g idArticulo null y se solicitan 1 uds.
//		carrito = sut.anhadirArticuloACarrito(null, 1);
//		assertEquals(null, carrito);
	}

}
