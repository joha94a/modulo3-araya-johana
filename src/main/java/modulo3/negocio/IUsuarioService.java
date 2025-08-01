package modulo3.negocio;

import java.util.List;
import modulo3.modelo.entidad.Usuario;

public interface IUsuarioService {
	public void nuevoUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public List<Usuario> listarUsuarios();
	public void eliminarUsuario(int id);
	public Usuario obtenerPorId(int id);
}
