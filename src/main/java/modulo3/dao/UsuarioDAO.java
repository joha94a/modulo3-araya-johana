package modulo3.dao;

import java.sql.SQLException;
import java.util.List;

import modulo3.modelo.entidad.Usuario;

public interface UsuarioDAO {
	
	public void guardar(Usuario user);
	public void modificar(Usuario user);
	public List<Usuario> listarTodos();
	public void eliminar(Usuario user);
	public Usuario listarPorId(int id) throws SQLException;
	
}
