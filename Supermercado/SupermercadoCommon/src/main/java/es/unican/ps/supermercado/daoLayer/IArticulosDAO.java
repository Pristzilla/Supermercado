package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import es.unican.ps.supermercado.entities.Articulo;

public interface IArticulosDAO {

	public Articulo crearArticulo (Articulo a);
	public Articulo modificarArticulo (Articulo a);
	public Articulo eliminarArticulo (Articulo a);
<<<<<<< .merge_file_DkUmXR
	public Articulo buscarArticuloPorID (Long id);
=======
	public Articulo buscarArticuloPorId (Long id);
>>>>>>> .merge_file_VrnMhQ
	public List<Articulo> articulos();
}
