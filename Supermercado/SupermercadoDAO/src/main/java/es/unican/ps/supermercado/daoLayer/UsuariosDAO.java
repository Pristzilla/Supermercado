package es.unican.ps.supermercado.daoLayer;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.entities.Usuario;

@Stateful
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
		em.remove(u);
		return u;
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
	    return (Usuario) q.getResultList();
	}

	@Override
	public Usuario buscarUsuarioPorDNI(String dni) {
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni");
	    return (Usuario) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u");
	    return (List<Usuario>) q.getResultList();
	}

}
