package modulo3.negocio;

import java.sql.SQLException;
import java.util.List;

import modulo3.dao.ClaseDAO;
import modulo3.dao.ReservaDAO;
import modulo3.dao.UsuarioDAO;
import modulo3.excepciones.ReservaException;
import modulo3.modelo.dto.ReservaDTO;
import modulo3.modelo.entidad.Reserva;


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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservaDTO> listarReservas() throws ReservaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarEstado(int id) throws ReservaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReservaDTO obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Reserva convertirAEntidad(ReservaDTO dto) {
		Reserva reserva = new Reserva();
		reserva.setUsuario(dto.getUsuario());
		reserva.setClase(dto.getClase());
		reserva.setFechaReserva(dto.getFechaReserva());
		reserva.setEstado(dto.getEstado());
		
		return reserva;
	}

}
