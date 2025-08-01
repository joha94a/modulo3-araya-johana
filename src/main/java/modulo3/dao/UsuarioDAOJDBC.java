package modulo3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modulo3.modelo.entidad.Usuario;

public class UsuarioDAOJDBC implements UsuarioDAO{
	
	//private static final String INSERT = "INSERT INTO usuario(nombre, email) VALUES (?, ?)";
   // private static final String SELECT_TODOS = "SELECT * FROM usuario";
	
	public Usuario listarPorId(int id) {
		 String sql = "SELECT * FROM usuario WHERE id = ?";
	     Usuario usuario = null;

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(sql)) {
	    	 
	    	 	ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                usuario = new Usuario(
	                    rs.getInt("id"),
	                    rs.getString("nombreUsuario"),
	                    rs.getString("email"),
	                    rs.getString("contraseña"),
	                    rs.getString("rol"),
	                    rs.getBoolean("estado")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return usuario;
	}
	
	
	
	public void guardar(Usuario user) {
	}
	
	public void modificar(Usuario user) {
	}
	
	public List<Usuario> listarTodos() {
		return null;
	}
	
	public void eliminar(Usuario user) {
	}
	


}
