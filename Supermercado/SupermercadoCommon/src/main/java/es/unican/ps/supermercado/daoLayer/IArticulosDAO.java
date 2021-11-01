package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import es.unican.ps.supermercado.entities.Articulo;

public interface IArticulosDAO {

	public Articulo crearArticulo (Articulo a);
	public Articulo modificarArticulo (Articulo a);
	public Articulo eliminarArticulo (Articulo a);
	public Articulo buscarArticuloPorId (Long id);
	public List<Articulo> articulos();
}
