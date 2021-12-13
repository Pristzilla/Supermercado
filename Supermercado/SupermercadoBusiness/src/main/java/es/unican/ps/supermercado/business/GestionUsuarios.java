package es.unican.ps.supermercado.business;

import javax.annotation.Resource;
import javax.ejb.EJB;

import javax.ejb.Stateless;

import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.unican.ps.supermercado.businessLayer.IGestionUsuariosLocal;
import es.unican.ps.supermercado.businessLayer.IGestionUsuariosRemote;
import es.unican.ps.supermercado.entities.Usuario;
import es.unican.ps.supermercado.daoLayer.IUsuariosDAORemote;

@Stateless
public class GestionUsuarios implements IGestionUsuariosLocal, IGestionUsuariosRemote {

	@EJB
	private IUsuariosDAORemote usuariosDAO;
	
	@Resource
	TimerService timerService;
	
	@PersistenceContext(unitName = "supermercadoPU")
	private EntityManager em;
	
	@Override
	public Usuario registraUsuario(Usuario u) {
		return usuariosDAO.crearUsuario(u);
	}
	
}
