package es.unican.ps.supermercado.businessLayer;

import java.util.List;
import es.unican.ps.supermercado.entities.Articulo;

public interface IConsultaArticulos {
	
	public List<Articulo> articulo (String nombre);
	public List<Articulo> articulos();
	public Articulo buscaArticulo(long id);

}
