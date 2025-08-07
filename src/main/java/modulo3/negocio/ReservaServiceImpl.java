package modulo3.negocio;

import java.sql.SQLException;
import java.util.List;

import modulo3.dao.ClaseDAO;
import modulo3.dao.ReservaDAO;
import modulo3.dao.UsuarioDAO;
import modulo3.excepciones.ReservaException;
import modulo3.excepciones.UsuarioException;
import modulo3.modelo.dto.ReservaDTO;
import modulo3.modelo.dto.UsuarioDTO;
import modulo3.modelo.entidad.Reserva;
import modulo3.modelo.entidad.Usuario;

public class ReservaServiceImpl implements IReservaService{
	
	private UsuarioDAO usuarioDAO;
	private ClaseDAO claseDAO;
	private ReservaDAO reservaDAO;
	
	public ReservaServiceImpl(UsuarioDAO usuarioDAO, ClaseDAO claseDAO, ReservaDAO reservaDAO) {
		this.usuarioDAO = usuarioDAO;
		this.claseDAO = claseDAO;
		this.reservaDAO = reservaDAO;
	}

	@Override
	public void nuevaReserva(ReservaDTO reserva) throws ReservaException, SQLException {
	
		//validar que el usuario exista en la base
		if(usuarioDAO.listarPorId(reserva.getUsuario().getIdUsuario()) != null) {
			throw new ReservaException("No se pudo generar la reserva, usuario no encontrado");
		}
		
		//validar que la clase exista en la base
		if(claseDAO.listarPorId(reserva.getClase().getIdClase()) != null) {
			throw new ReservaException("No se pudo generar la reserva, clase no encontrada");
		}
	
		// antes de crear una reserva valido que el mismo usuario ya no tenga una asignada ese dia
		if(reservaDAO.yaReservado(reserva.getUsuario().getIdUsuario(), reserva.getFechaReserva())) {
			throw new ReservaException("El usuario ya reservo en esta fecha y hora");
		}
		
		try {
			reservaDAO.guardar(convertirAEntidad(reserva));
		} catch (SQLException e) {
			throw new RuntimeException("No se pudo crear la reserva", e);
		}

	}

	@Override
	public void modificarReserva(ReservaDTO reserva) throws ReservaException {
		try {
			Reserva reservaExistente = reservaDAO.listarPorId(reserva.getIdReserva());

			if (reservaExistente == null) {
				throw new ReservaException("La reserva no existe.");
			}

			// antes de crear una reserva valido que el mismo usuario ya no tenga una asignada ese dia
			if(reservaDAO.yaReservado(reserva.getUsuario().getIdUsuario(), reserva.getFechaReserva())) {
				throw new ReservaException("El usuario ya reservo en esta fecha y hora");
			}
			
			reservaExistente.setUsuario(reserva.getUsuario());
			reservaDAO.modificar(reservaExistente);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReservaException("Error al modificar la reserva: " + e.getMessage());
		}
		
	}

	@Override
	public List<ReservaDTO> listarReservas() throws ReservaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarEstado(int id) throws ReservaException {
		try {
			Reserva reservaExistente = reservaDAO.listarPorId(id);

			if (reservaExistente == null) {
				throw new ReservaException("La reserva no existe.");
			}
			//cambia el estado a "CANCELADA"
			reservaDAO.eliminar(id);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReservaException("Error al modificar la reserva: " + e.getMessage());
		}
		
	}

	@Override
	public ReservaDTO obtenerPorId(int id) throws ReservaException {
		try {
			Reserva reserva = reservaDAO.listarPorId(id);
			if (reserva != null) {
				return convertirADTO(reserva);
			}
			else {
	            throw new ReservaException("No se encontró una reserva con el ID: " + id);
	        }
		} catch (SQLException e) {
			throw new ReservaException("Error al buscar la reserva: " + e.getMessage());
		}
	}
	
	@Override
	public Reserva convertirAEntidad(ReservaDTO dto) {
		Reserva reserva = new Reserva();
		reserva.setUsuario(dto.getUsuario());
		reserva.setClase(dto.getClase());
		reserva.setFechaReserva(dto.getFechaReserva());
		reserva.setEstado(dto.getEstado());
		
		return reserva;
	}
	
	@Override
	public ReservaDTO convertirADTO(Reserva reserva) {
		ReservaDTO dto = new ReservaDTO();
		
		dto.setIdReserva(reserva.getIdReserva());
		dto.setUsuario(reserva.getUsuario());
		dto.setClase(reserva.getClase());
		dto.setFechaReserva(reserva.getFechaReserva());
		dto.setEstado(reserva.getEstado());
		return dto;
	}

}
