package modulo3.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import modulo3.modelo.entidad.Reserva;

public interface ReservaDAO {
	public void guardar(Reserva reserva) throws SQLException;
	public void modificar(Reserva reserva) throws SQLException;
	public List<Reserva> listarTodas() throws SQLException;
	public void eliminar(int id) throws SQLException;
	public Reserva listarPorId(int id) throws SQLException;
	public boolean yaReservado(int idUsuario, LocalDateTime fecha) throws SQLException; //validar que el usuario no haya reservado esa misma clase
}
