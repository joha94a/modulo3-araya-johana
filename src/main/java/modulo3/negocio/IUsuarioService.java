package modulo3.negocio;

import java.util.List;

import modulo3.modelo.dto.UsuarioDTO;

public interface IUsuarioService {
	public void nuevoUsuario(UsuarioDTO usuario);
	public void modificarUsuario(UsuarioDTO usuario);
	public List<UsuarioDTO> listarUsuarios();
	public void eliminarUsuario(int id);
	public UsuarioDTO obtenerPorId(int id);
}
