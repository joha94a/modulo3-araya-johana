package modulo3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modulo3.modelo.entidad.Usuario;

public class UsuarioDAOJDBC implements UsuarioDAO{
	
	private static final String INSERT = "INSERT INTO usuario (nombreUsuario,email,contraseña,rol,estado) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_TODOS = "SELECT * FROM usuario";
	private static final String CAMBIAR_ESTADO = "UPDATE usuario SET estado = FALSE WHERE idUsuario = ?";
	private static final String LISTAR_POR_ID = "SELECT * FROM usuario WHERE idUsuario = ?";
	private static final String UPDATE = "UPDATE usuario SET nombreUsuario = ?, email = ?, contraseña = ?, rol = ?, estado = ? WHERE idUsuario = ?";
	private static final String ENCONTRAR_EMAIL = "SELECT COUNT(*) FROM usuario WHERE email = ?";

	
	public Usuario listarPorId(int id) {
	     Usuario usuario = null;

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(LISTAR_POR_ID)) {
	    	 
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
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(INSERT)) {
			
		        ps.setString(1, user.getNombreUsuario());
		        ps.setString(2, user.getEmail());
		        ps.setString(3, user.getContraseña());
		        ps.setString(4, user.getRol());
		        ps.setBoolean(5, user.getEstado());
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	public void modificar(Usuario user) {
	
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			
	        ps.setString(1, user.getNombreUsuario());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getContraseña());
	        ps.setString(4, user.getRol());
	        ps.setBoolean(5, user.getEstado());
	        ps.setInt(6, user.getIdUsuario());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = new ArrayList<>();

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {
	    	 
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Usuario usuario = new Usuario(
	                    rs.getInt("id"),
	                    rs.getString("nombreUsuario"),
	                    rs.getString("email"),
	                    rs.getString("contraseña"),
	                    rs.getString("rol"),
	                    rs.getBoolean("estado")
	                );
	                usuarios.add(usuario);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return usuarios;
		
	}
	
	//cambia el estado a falso
	public void eliminar(int id) {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(CAMBIAR_ESTADO)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	@Override
	public boolean existeEmail(String email) throws SQLException {

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(ENCONTRAR_EMAIL)) {
	    	 
	    	 	ps.setString(1, email);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	}
	
}
