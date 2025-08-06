package modulo3.negocio;

import java.util.List;

import modulo3.modelo.entidad.Clase;

public interface IClaseService {
 	public void nuevaClase(Clase clase);
	public void modificarReserva(Clase clase);
	public List<Clase> listarReservas();
	public boolean cambiarEstado(int id);
	public Clase obtenerPorId(int id);
}
