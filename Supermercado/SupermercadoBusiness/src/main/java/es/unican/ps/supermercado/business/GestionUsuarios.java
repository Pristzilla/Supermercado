package es.unican.ps.supermercado.business;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import es.unican.ps.supermercado.businessLayer.IGestionUsuariosLocal;
import es.unican.ps.supermercado.businessLayer.IGestionUsuariosRemote;
import es.unican.ps.supermercado.entities.Usuario;
import es.unican.ps.supermercado.daoLayer.IUsuariosDAOLocal;

@Stateful
public class GestionUsuarios implements IGestionUsuariosLocal, IGestionUsuariosRemote {

	@EJB
	private IUsuariosDAOLocal usuariosDAO;
	
	@Override
	public Usuario registraUsuario(Usuario u) {
		return usuariosDAO.crearUsuario(u);
	}

}
