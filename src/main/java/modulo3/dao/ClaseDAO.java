package modulo3.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import modulo3.modelo.entidad.Clase;

public interface ClaseDAO {
    public void guardar(Clase clase) throws SQLException;
	public void modificar(Clase clase) throws SQLException;
	public List<Clase> listarTodas() throws SQLException;
	public void eliminar(int id) throws SQLException;
	public Clase listarPorId(int id) throws SQLException;
	public boolean horarioEnUso(LocalDateTime horario) throws SQLException;

}
