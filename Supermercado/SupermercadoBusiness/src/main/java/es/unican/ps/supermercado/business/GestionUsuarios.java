package es.unican.ps.supermercado.business;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateful;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
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
	
	@PersistenceContext(unitName = "supermercadoPU")
	private EntityManager em;
	
	@Override
	public Usuario registraUsuario(Usuario u) {
		return usuariosDAO.crearUsuario(u);
	}
	
	// programar el timer mensual como uno que recorra todos los usuarios actualizando 
	// el valor de compras mensuales
	public void inicializaTimer() {
		
		ScheduleExpression dia1mes = new ScheduleExpression().dayOfMonth(1);
		
		timerService.createCalendarTimer(dia1mes, new TimerConfig());
		
	}
	
	/**
	 * Metodo que resetea las compras mensuales en los usuarios.
	 */
	@Timeout
	private void reseteaComprasMensuales(Timer timer) {
		List<Usuario> usuarios = usuariosDAO.usuarios();
		for (Usuario u: usuarios) {
			u.setComprasMensuales(0);
			usuariosDAO.modificarUsuario(u);
		}
	}

}
