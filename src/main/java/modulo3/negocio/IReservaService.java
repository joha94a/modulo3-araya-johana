package modulo3.negocio;

import java.sql.SQLException;
import java.util.List;

import modulo3.excepciones.ReservaException;
import modulo3.modelo.dto.ReservaDTO;


public interface IReservaService {
 	public void nuevaReserva(ReservaDTO reserva) throws ReservaException, SQLException;
	public void modificarReserva(ReservaDTO reserva) throws ReservaException ;
	public List<ReservaDTO> listarReservas() throws ReservaException;
	public void cambiarEstado(int id) throws ReservaException;
	public ReservaDTO obtenerPorId(int id);

}
