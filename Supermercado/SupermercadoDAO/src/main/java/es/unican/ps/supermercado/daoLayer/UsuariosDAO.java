package es.unican.ps.supermercado.daoLayer;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.entities.Usuario;

@Stateless
public class UsuariosDAO implements IUsuariosDAOLocal, IUsuariosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;
	
	@Override
	public Usuario crearUsuario(Usuario u) {
		try  {
			em.persist(u);
			return u;
		} catch (EntityExistsException e) {
			return null;
		}
	}

	@Override
	public Usuario modificarUsuario(Usuario u) {
		em.merge(u);
		return u;
	}

	@Override
	public Usuario eliminarUsuario(Usuario u) {
		Usuario us = em.find(Usuario.class, u.getId());
		if (us == null) {
			return null;
		}
		em.remove(us);
		return us;
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario buscarUsuarioPorDNI(String dni) {
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni");
		q.setParameter("dni", dni);
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u");
	    return q.getResultList();
	}

}
