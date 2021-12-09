/**
 *
 * Author: Sara Grela y Javier Barquin
 * Date: 3 dic. 2021
 *
 */
package es.unican.ps.supermercado.business;

import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercado.businessLayer.IGestionPedidosRemote;
import es.unican.ps.supermercado.daoLayer.IArticulosDAORemote;

/**
 * @author barquinj
 *
 */
public class GestionPedidosITest {

	private static EJBContainer ec;
	private static IArticulosDAORemote articulosDAO;
	private static IGestionPedidosRemote sut;
	
	@BeforeClass
	public static void initContainer() throws Exception {
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
