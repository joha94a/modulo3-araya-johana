package modulo3.dao;

import java.sql.SQLException;
import java.util.List;

import modulo3.modelo.entidad.Usuario;

public interface UsuarioDAO {
	
	public void guardar(Usuario user) throws SQLException;
	public void modificar(Usuario user) throws SQLException;
	public List<Usuario> listarTodos() throws SQLException;
	public void eliminar(int id) throws SQLException;
	public Usuario listarPorId(int id) throws SQLException;
	public boolean existeEmail(String email) throws SQLException;
	
}
