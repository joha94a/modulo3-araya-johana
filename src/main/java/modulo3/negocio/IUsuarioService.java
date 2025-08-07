package modulo3.negocio;

import java.util.List;

import modulo3.excepciones.UsuarioException;
import modulo3.modelo.dto.UsuarioDTO;
import modulo3.modelo.entidad.Usuario;

public interface IUsuarioService {
	public void nuevoUsuario(UsuarioDTO usuario) throws UsuarioException;
	public void modificarUsuario(UsuarioDTO usuario) throws UsuarioException ;
	public List<UsuarioDTO> listarUsuarios() throws UsuarioException;
	public void eliminarUsuario(int id) throws UsuarioException;
	public UsuarioDTO obtenerPorId(int id);
	public UsuarioDTO convertirADTO(Usuario usuario);
	public Usuario convertirAEntidad(UsuarioDTO dto);
	public boolean validarEmailRepetido(String email);
}
