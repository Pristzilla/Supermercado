package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.entities.Articulo;

@Stateful
public class ArticulosDAO implements IArticulosDAOLocal, IArticulosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	@Override
	public Articulo crearArticulo(Articulo a) {
		try  {
			em.persist(a);
			return a;
		} catch (EntityExistsException e) {
			return null;
		}
	}

	@Override
	public Articulo modificarArticulo(Articulo a) {
		em.merge(a);
		return a;
	}

	@Override
	public Articulo eliminarArticulo(Articulo a) {
		em.remove(a);
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> buscarArticuloPorNombre(String nombre) {
		Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.nombre LIKE '%:nombre%'");
		q.setParameter("nombre", nombre);
		return (List<Articulo>) q.getResultList();
	}

	@Override
	public Articulo buscarArticuloPorId(Long id) {
		Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.id = :id");
		q.setParameter("id", id);
		try {
			return (Articulo) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> articulos() {
		Query q = em.createQuery("SELECT a FROM Articulo a");
		return (List<Articulo>) q.getResultList();
	}

}
