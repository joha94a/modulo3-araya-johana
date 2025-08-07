package modulo3.negocio;

import java.util.List;

import modulo3.modelo.dto.ClaseDTO;
import modulo3.modelo.entidad.Clase;

public interface IClaseService {
 	public void nuevaClase(ClaseDTO clase);
	public void modificarClase(ClaseDTO clase);
	public List<ClaseDTO> listarClase();
	public boolean cambiarEstado(int id);
	public ClaseDTO obtenerPorId(int id);
	public ClaseDTO convertirADTO(Clase clase);
	public Clase convertirAEntidad(ClaseDTO dto);
}
