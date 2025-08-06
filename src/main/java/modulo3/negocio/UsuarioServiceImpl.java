package modulo3.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modulo3.dao.UsuarioDAO;
import modulo3.excepciones.UsuarioException;
import modulo3.modelo.dto.UsuarioDTO;
import modulo3.modelo.entidad.Usuario;

public class UsuarioServiceImpl implements IUsuarioService {

	private UsuarioDAO usuarioDAO;

	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public void nuevoUsuario(UsuarioDTO usuario) throws UsuarioException {
		// antes de crear usuario tengo que validar posibles duplicados, por ej, mail

		if (validarEmailRepetido(usuario.getEmail())) {
			throw new UsuarioException("El email ya se encuentra registrado: " + usuario.getEmail());
		}

		try {
			usuarioDAO.guardar(convertirAEntidad(usuario));
		} catch (SQLException e) {
			throw new RuntimeException("No se pudo crear usuario", e);
		}

	}

	@Override
	public void modificarUsuario(UsuarioDTO usuario) throws UsuarioException {
		try {
			Usuario usuarioExistente = usuarioDAO.listarPorId(usuario.getIdUsuario());

			if (usuarioExistente == null) {
				throw new UsuarioException("El usuario con ID " + usuario.getIdUsuario() + " no existe.");
			}

			if (!usuario.getEmail().equalsIgnoreCase(usuarioExistente.getEmail())) {
				boolean emailEnUso = usuarioDAO.existeEmail(usuario.getEmail());
				if (emailEnUso) {
					throw new UsuarioException("El email ya está en uso por otro usuario.");
				}
			}

			usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
			usuarioExistente.setEmail(usuario.getEmail());
			usuarioExistente.setContraseña(usuario.getContraseña());
			usuarioExistente.setRol(usuario.getRol());
			usuarioExistente.setEstado(usuario.isEstado());

			usuarioDAO.modificar(usuarioExistente);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsuarioException("Error al modificar el usuario: " + e.getMessage());
		}
	}

	@Override
	public List<UsuarioDTO> listarUsuarios() throws UsuarioException {
		List<UsuarioDTO> listaDTO = new ArrayList<>();

		try {
			List<Usuario> usuarios = usuarioDAO.listarTodos();

			for (Usuario u : usuarios) {
				UsuarioDTO dto = new UsuarioDTO();
				dto.setIdUsuario(u.getIdUsuario());
				dto.setNombreUsuario(u.getNombreUsuario());
				dto.setEmail(u.getEmail());
				dto.setRol(u.getRol());
				dto.setEstado(u.getEstado());

				listaDTO.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw new UsuarioException("Error al listar los usuarios: " + e.getMessage());
		}

		return listaDTO;
	}

	@Override
	public void eliminarUsuario(int id) throws UsuarioException {
		try {
			Usuario usuarioExistente = usuarioDAO.listarPorId(id);

			if (usuarioExistente == null) {
				throw new UsuarioException("El usuario no existe.");
			}
			usuarioDAO.eliminar(id);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsuarioException("Error al eliminar usuario " + e.getMessage());
		}

	}

	@Override
	public UsuarioDTO obtenerPorId(int id) {
		// modificar, si el usuario da null no deberia devolver - tiene que salir un
		// error
		try {
			Usuario usuario = usuarioDAO.listarPorId(id);
			if (usuario != null) {
				return convertirADTO(usuario);
			}
		} catch (SQLException e) {
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
		try {
			return usuarioDAO.existeEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException("Error al validar si el email está repetido", e);
		}
	}

}
