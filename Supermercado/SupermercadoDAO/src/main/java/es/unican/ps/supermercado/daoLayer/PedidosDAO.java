package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
<<<<<<< HEAD
=======

>>>>>>> 79c9aa8cdb984ab7665385eff3cece354b2d339a
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
		Pedido ped = em.find(Pedido.class, p.getId());
		if (ped == null) {
			return null;
		}
		em.remove(ped);
		return ped;
	}

	@Override
	public Pedido buscarPedidoPorId(Long id) {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.id = :id");
		q.setParameter("id", id);
		try {
			return (Pedido) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Pedido buscarPedidoPorReferencia(String ref) {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.ref = :ref");
		q.setParameter("ref", ref);
		try {
			return (Pedido) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Pedido buscarPrimerPedidoPendiente() {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.estado = 'PENDIENTE' ORDER BY p.fecha DESC");
	    return (Pedido) q.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> pedidos() {
		Query q = em.createQuery("SELECT p FROM Pedido p");
	    return q.getResultList();
	}

}
