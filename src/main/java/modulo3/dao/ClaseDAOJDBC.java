package modulo3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modulo3.modelo.entidad.Clase;
import modulo3.modelo.entidad.Usuario;

public class ClaseDAOJDBC implements ClaseDAO{
	
	private static final String INSERT = "INSERT INTO clase (nombre,capacidad,idProfesor,horario,duracion,estado) VALUES (?, ?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE clase SET nombre = ?, capacidad = ?, idProfesor = ?, horario = ?, duracion = ?, estado = ? WHERE id = ?";
	private static final String SELECT_TODOS = "SELECT * FROM clase";
	private static final String CAMBIAR_ESTADO = "UPDATE clase SET estado = FALSE WHERE id = ?";
	private static final String LISTAR_POR_ID = "SELECT * FROM clase WHERE id = ?";
	private static final String ENCONTRAR_HORARIO = "SELECT COUNT(*) FROM clase WHERE horario = ?";

	@Override
	public void guardar(Clase clase) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(INSERT)) {
			
		        ps.setString(1, clase.getNombre());
		        ps.setInt(2, clase.getCapacidad());
		        ps.setInt(3, clase.getProfesor().getIdUsuario());
		        ps.setTimestamp(4, Timestamp.valueOf(clase.getDiaHora()));
		        ps.setInt(5, clase.getDuracionMin());
		        ps.setBoolean(6, clase.getEstado());
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}

	@Override
	public void modificar(Clase clase) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			
			ps.setString(1, clase.getNombre());
	        ps.setInt(2, clase.getCapacidad());
	        ps.setInt(3, clase.getProfesor().getIdUsuario());
	        ps.setTimestamp(4, Timestamp.valueOf(clase.getDiaHora()));
	        ps.setInt(5, clase.getDuracionMin());
	        ps.setBoolean(6, clase.getEstado());
	        ps.setInt(6, clase.getIdClase());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public List<Clase> listarTodas() throws SQLException {
		List<Clase> clases = new ArrayList<>();

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {
	    	 
	            ResultSet rs = ps.executeQuery();
	            
	            //solo cargo el id profesor y despues manejo la carga completa del objeto en el service
	            while (rs.next()) {
	            	Usuario profesor = new Usuario();
	            	profesor.setIdUsuario(rs.getInt("idProfesor"));
	            	
	                Clase clase = new Clase(
	                    rs.getInt("id"),
	                    rs.getString("nombre"),
	                    rs.getInt("capacidad"),
	                    profesor,
	                    rs.getTimestamp("horario").toLocalDateTime(),
	                    rs.getInt("duracion"),
	                    rs.getBoolean("estado")
	                );
	                clases.add(clase);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return clases;
	}

	@Override
	public void eliminar(int id) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(CAMBIAR_ESTADO)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public Clase listarPorId(int id) throws SQLException {
		 Clase clase = null;

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(LISTAR_POR_ID)) {
	    	 
	    	 	ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	Usuario profesor = new Usuario();
	            	profesor.setIdUsuario(rs.getInt("idProfesor"));
	            	
	            	clase = new Clase(
	            			rs.getInt("id"),
		                    rs.getString("nombre"),
		                    rs.getInt("capacidad"),
		                    profesor,
		                    rs.getTimestamp("horario").toLocalDateTime(),
		                    rs.getInt("duracion"),
		                    rs.getBoolean("estado")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	     return clase;
	}

	@Override
	public boolean horarioEnUso(LocalDateTime horario) throws SQLException {
	     try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(ENCONTRAR_HORARIO)) {
		    	 
		    	 	ps.setTimestamp(1, Timestamp.valueOf(horario));
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
