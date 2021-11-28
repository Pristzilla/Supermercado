package es.unican.ps.supermercado.business;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.unican.ps.supermercado.businessLayer.IGestionUsuariosLocal;
import es.unican.ps.supermercado.businessLayer.IGestionUsuariosRemote;
import es.unican.ps.supermercado.entities.Usuario;
import es.unican.ps.supermercado.daoLayer.IUsuariosDAOLocal;

@Stateful
public class GestionUsuarios implements IGestionUsuariosLocal, IGestionUsuariosRemote {

	@EJB
	private IUsuariosDAOLocal usuariosDAO;
	
	@Resource
	TimerService timerService;
	
	@PersistenceContext(unitName = "usuarios")
	private EntityManager em;
	
	@Override
	public Usuario registraUsuario(Usuario u) {
		return usuariosDAO.crearUsuario(u);
	}
	
	// programar el timer mensual como uno que recorra todos los usuarios actualizando 
	// el valor de compras mensuales
	// o programarlo como un timer para cada usuario.
	private void resetaComprasMensuales() {
		List<Usuario> usuarios = usuariosDAO.usuarios();
		for (Usuario u: usuarios) {
			u.setComprasMensuales(0);
			usuariosDAO.modificarUsuario(u);
		}
	}

}
