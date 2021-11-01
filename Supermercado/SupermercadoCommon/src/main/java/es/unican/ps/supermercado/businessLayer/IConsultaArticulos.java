package es.unican.ps.supermercado.businessLayer;

import java.util.List;
import es.unican.ps.supermercado.entities.Articulo;

public interface IConsultaArticulos {
	
	public List<Articulo> articulo (String nombre);

}
