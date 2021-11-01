package es.unican.ps.supermercado.business;

import java.util.List;
import javax.ejb.Stateful;
import es.unican.ps.supermercado.businessLayer.*;
import es.unican.ps.supermercado.daoLayer.IArticulosDAO;
import es.unican.ps.supermercado.entities.Articulo;

@Stateful
public class GestionArticulos implements IConsultaArticulosLocal, IConsultaArticulosRemote {

	private IArticulosDAO articulosDAO;
	
	public List<Articulo> articulo(String nombre) {
		return articulosDAO.buscarArticuloPorNombre(nombre);
	}

}
