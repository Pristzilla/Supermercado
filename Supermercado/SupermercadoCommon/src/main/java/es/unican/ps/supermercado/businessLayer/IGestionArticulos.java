package es.unican.ps.supermercado.businessLayer;

import es.unican.ps.supermercado.entities.Articulo;

public interface IGestionArticulos {

	public Articulo crearArticulo (Articulo a);
	public Articulo modificarArticulo (Articulo a);
	public Articulo eliminarArticulo (Articulo a);
}
