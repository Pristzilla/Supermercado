package es.unican.ps.supermercado.daoLayer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import es.unican.ps.supermercado.entities.Pedido;

public class PedidosDAOTest {

	private PedidosDAO sut;
	@Mock
	private EntityManager mockEM;
	@Mock
	private Query mockQuery;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();  
	
	Pedido p1;
	private String ref1 = "referencia1";
	private String ref2 = "referencia2";

	@Before
	public void setUp() throws Exception {
		sut = new PedidosDAO();
		p1 = new Pedido(Pedido.Estado.NO_CONFIRMADO);
		p1.setRef(ref1);
	}

	@Test
	public void buscarPedidoPorReferencia() {
//		
//		Pedido p;
//
//		// Prueba UGIC.1x
//		try {
//			when(mockEM.createQuery("SELECT p FROM Pedido p WHERE p.ref = " + ref1)).thenReturn(mockQuery);
//			when(mockQuery.getSingleResult()).thenReturn(p1);
//			p = sut.buscarPedidoPorReferencia(ref1);
//			assertEquals(p, p1);
//		} catch (OperacionNoValida e) {
//			// No puede fallar
//			fail();
//		}
//		
//		// Prueba UGIC.1y
//		try {
//			when(mockEM.createQuery("SELECT p FROM Pedido p WHERE p.ref = " + ref2)).thenReturn(mockQuery);
//			when(mockQuery.getSingleResult()).thenReturn(null);
//			p = sut.buscarPedidoPorReferencia(ref2);
//			assertEquals(p, null);
//			fail();
//		} catch (OperacionNoValida e) {
//			// Debe fallar
//			
//		}
	}

}
