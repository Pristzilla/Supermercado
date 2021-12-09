package es.unican.ps.supermercado.daoLayer;

// import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import es.unican.ps.supermercado.entities.Pedido;

public class PedidosDAOTest {

	private PedidosDAO sut;

	@Before
	public void setUp() throws Exception {
		sut = new PedidosDAO();
	}

	/**
	 * Nota: al ejecutarse este test, existen 3 pedidos pendientes:
	 * Pedido 1: '10/12/2021', ref: '12345678AA' 
	 * Pedido 2: '11/12/2021', ref: '12345678CC',
	 * Pedido 3: '12/12/2021', ref: '12345678DD' .
	 */
	@Test
	public void buscarPedidoPorReferencia() {

		// Prueba UGIC.1u 
		try {
			assertEquals(sut.buscarPrimerPedidoPendiente(), null);
		} catch (OperacionNoValida e) {
			// No puede fallar
			fail();
		}

		// Anhadimos pedidos pendientes
		this.loadData();

		// Prueba UGIC.1t 
		String referencia = "12345678AA";
		Pedido p = sut.buscarPedidoPorReferencia(referencia);
		try {
			assertEquals(sut.buscarPrimerPedidoPendiente(), p);
		} catch (OperacionNoValida e) {
			// No puede fallar
			fail();
		}

		// Eliminar dos pedidos
		sut.eliminarPedido(p);
		referencia = "12345678CC";
		p = sut.buscarPedidoPorReferencia(referencia);
		sut.eliminarPedido(p);

		// Prueba UGIC.1t 
		referencia = "12345678DD";
		p = sut.buscarPedidoPorReferencia(referencia);
		try {
			assertEquals(sut.buscarPrimerPedidoPendiente(), p);
		} catch (OperacionNoValida e) {
			// No puede fallar
			fail();
		}

	}

}
