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
import modulo3.modelo.entidad.Reserva;
import modulo3.modelo.entidad.Usuario;

public class ReservaDAOJDBC implements ReservaDAO{
	
		private static final String INSERT = "INSERT INTO reserva (idUsuario,idClase,fechaReserva,estado) VALUES (?, ?, ?, ?)";
		private static final String UPDATE = "UPDATE reserva SET idUsuario = ?, idClase = ?, fechaReserva = ?, estado = ? WHERE idReserva = ?";
		private static final String SELECT_TODOS = "SELECT * FROM reserva";
		private static final String CAMBIAR_ESTADO = "UPDATE reserva SET estado = 'CANCELADA' WHERE idReserva = ?";
		private static final String LISTAR_POR_ID = "SELECT * FROM reserva WHERE idReserva = ?";
		private static final String ENCONTRAR_HORARIO = "SELECT COUNT(*) FROM reserva WHERE id_usuario = ? AND horario = ?";


	@Override
	public void guardar(Reserva reserva) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(INSERT)) {
			
		        ps.setInt(1, reserva.getUsuario().getIdUsuario());
		        ps.setInt(2, reserva.getClase().getIdClase());
		        ps.setTimestamp(3, Timestamp.valueOf(reserva.getFechaReserva()));
		        ps.setString(4, reserva.getEstado());
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}

	@Override
	public void modificar(Reserva reserva) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		          PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			
			ps.setInt(1, reserva.getUsuario().getIdUsuario());
	        ps.setInt(2, reserva.getClase().getIdClase());
	        ps.setTimestamp(3, Timestamp.valueOf(reserva.getFechaReserva()));
	        ps.setString(4, reserva.getEstado());
	        ps.setInt(6, reserva.getIdReserva());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Reserva> listarTodas() throws SQLException {
		List<Reserva> reservas = new ArrayList<>();

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {
	    	 
	            ResultSet rs = ps.executeQuery();
	            
	            //solo cargo el id usuario y clase, despues manejo la carga completa del objeto en el service
	            while (rs.next()) {
	            	Usuario usuario = new Usuario();
	            	usuario.setIdUsuario(rs.getInt("idUsuario"));
	            	
	            	Clase clase = new Clase();
	            	clase.setIdClase(rs.getInt("idClase"));
	            	
	                Reserva reserva = new Reserva(
	                    rs.getInt("id"),
	                    usuario,
	                    clase,
	                    rs.getTimestamp("fechaReserva").toLocalDateTime(),
	                    rs.getString("estado")
	                );
	                reservas.add(reserva);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return reservas;
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
	public Reserva listarPorId(int id) throws SQLException {
		Reserva reserva = null;

	     try (Connection conn = ConexionBD.getConexion();
	          PreparedStatement ps = conn.prepareStatement(LISTAR_POR_ID)) {
	    	 
	    	 	ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	Usuario usuario = new Usuario();
	            	usuario.setIdUsuario(rs.getInt("idUsuario"));
	            	
	            	Clase clase = new Clase();
	            	clase.setIdClase(rs.getInt("idClase"));
	            	
	            	reserva = new Reserva(
	            			rs.getInt("id"),
		                    usuario,
		                    clase,
		                    rs.getTimestamp("fechaReserva").toLocalDateTime(),
		                    rs.getString("estado")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	     return reserva;
	}

	@Override
	public boolean yaReservado(int idUsuario, LocalDateTime fecha) throws SQLException {
		try (Connection conn = ConexionBD.getConexion();
		         PreparedStatement ps = conn.prepareStatement(ENCONTRAR_HORARIO)) {

		        ps.setInt(1, idUsuario);
		        ps.setTimestamp(2, Timestamp.valueOf(fecha));

		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            return rs.getInt(1) > 0; 
		        }
		    }

		    return false;
	}

}
