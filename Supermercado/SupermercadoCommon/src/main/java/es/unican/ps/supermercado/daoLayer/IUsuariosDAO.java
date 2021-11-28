package es.unican.ps.supermercado.daoLayer;

import java.util.List;

import es.unican.ps.supermercado.entities.Usuario;

public interface IUsuariosDAO {

	public Usuario crearUsuario (Usuario u);
	public Usuario modificarUsuario (Usuario u);
	public Usuario eliminarUsuario (Usuario u);
	public Usuario buscarUsuarioPorId (Long id);
	public Usuario buscarUsuarioPorDNI (String dni);
	public List<Usuario> usuarios();
}
