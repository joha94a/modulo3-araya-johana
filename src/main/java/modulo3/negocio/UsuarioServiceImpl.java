package modulo3.negocio;

import java.sql.SQLException;
import java.util.List;

import modulo3.dao.UsuarioDAO;
import modulo3.modelo.dto.UsuarioDTO;
import modulo3.modelo.entidad.Usuario;

public class UsuarioServiceImpl implements IUsuarioService{
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public void nuevoUsuario(UsuarioDTO usuario) {
		//antes de crear usuario tengo que validar posibles duplicados, por ej, mail
		
		
	}

	@Override
	public void modificarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioDTO obtenerPorId(int id) {
		try {
			Usuario usuario = usuarioDAO.listarPorId(id);
			if(usuario != null) {
				return convertirADTO(usuario);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static UsuarioDTO convertirADTO(Usuario usuario) {
	    UsuarioDTO dto = new UsuarioDTO();
	    dto.setIdUsuario(usuario.getIdUsuario());
	    dto.setNombreUsuario(usuario.getNombreUsuario());
	    dto.setEmail(usuario.getEmail());
	    dto.setRol(usuario.getRol());
	    dto.setEstado(usuario.getEstado());
	    return dto;
	}
	
	public static Usuario convertirAEntidad(UsuarioDTO dto) {
	    Usuario usuario = new Usuario();
	    usuario.setIdUsuario(dto.getIdUsuario());
	    usuario.setNombreUsuario(dto.getNombreUsuario());
	    usuario.setEmail(dto.getEmail());
	    usuario.setRol(dto.getRol());
	    usuario.setEstado(dto.isEstado());
	    // Contraseña queda sin setear porque no está en el DTO
	    return usuario;
	}
	
	public boolean validarEmailRepetido(String email) {
		//usuarioDAO.
		return false;
	}

}
