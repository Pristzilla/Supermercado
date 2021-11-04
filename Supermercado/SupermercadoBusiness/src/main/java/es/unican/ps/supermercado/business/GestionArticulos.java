package es.unican.ps.supermercado.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import es.unican.ps.supermercado.businessLayer.*;
import es.unican.ps.supermercado.daoLayer.*;
import es.unican.ps.supermercado.entities.Articulo;

@Stateful
public class GestionArticulos implements IGestionArticulosLocal, IGestionArticulosRemote,
										 IConsultaArticulosLocal, IConsultaArticulosRemote {

	@EJB
	private IArticulosDAOLocal articulosDAO;
	
	@Override
	public Articulo crearArticulo(Articulo a) {
		List<Articulo> articulos = articulosDAO.articulos();
		if (articulos.contains(a))
			return null;
		return articulosDAO.crearArticulo(a);
	}

	@Override
	public Articulo modificarArticulo(Articulo a) {
		List<Articulo> articulos = articulosDAO.articulos();
		if (!articulos.contains(a))
			return null;
		return articulosDAO.modificarArticulo(a);
	}

	@Override
	public Articulo eliminarArticulo(Articulo a) {
		List<Articulo> articulos = articulosDAO.articulos();
		if (!articulos.contains(a)) {
			return null;
		}
		else 
			return articulosDAO.eliminarArticulo(a);
	}
	
	/**
	 * Metodo que devuelve los articulos que coinciden con un texto
	 * @param nombre texto a buscar
	 */
	public List<Articulo> articulo(String nombre) {
		return articulosDAO.buscarArticuloPorNombre(nombre);
	}


}
