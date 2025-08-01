package modulo3.negocio;

import java.sql.SQLException;
import java.util.List;

import modulo3.dao.UsuarioDAO;
import modulo3.modelo.entidad.Usuario;

public class UsuarioServiceImpl implements IUsuarioService{
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public void nuevoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario obtenerPorId(int id) {
		try {
			Usuario usuario = usuarioDAO.listarPorId(id);
			if(usuario != null) {
				return usuario;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
