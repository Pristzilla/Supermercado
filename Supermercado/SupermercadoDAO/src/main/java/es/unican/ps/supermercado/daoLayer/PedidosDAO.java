package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.entities.Pedido;

@Stateful
public class PedidosDAO implements IPedidosDAOLocal, IPedidosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;
	
	@Override
	public Pedido crearPedido(Pedido p) {
		try  {
			em.persist(p);
			return p;
		} catch (EntityExistsException e) {
			return null;
		}
	}

	@Override
	public Pedido modificarPedido(Pedido p) {
		em.merge(p);
		return p;
	}

	@Override
	public Pedido eliminarPedido(Pedido p) {
		em.remove(p);
		return p;
	}

	@Override
	public Pedido buscarPedidoPorId(Long id) {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.id = :id");
	    return (Pedido) q.getResultList();
	}

	@Override
	public Pedido buscarPedidoPorReferencia(String ref) {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.ref = :ref");
	    return (Pedido) q.getResultList();
	}


	@Override
	public Pedido buscarPrimerPedidoPendiente() {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.estado = 'PENDIENTE'");
	    return (Pedido) q.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> pedidos() {
		Query q = em.createQuery("SELECT p FROM Pedido p");
	    return (List<Pedido>) q.getResultList();
	}

}
