package modulo3.modelo.entidad;

import java.time.LocalDateTime;

public class Clase {
	private int idClase;
    private String nombre;
    private int capacidad;
    private Usuario profesor;
    private LocalDateTime diaHora;
    private int duracionMin;
    private boolean estado;
    
    
	public Clase(int idClase, String nombre, int capacidad, Usuario profesor, LocalDateTime diaHora, int duracionMin,
			boolean estado) {
		super();
		this.idClase = idClase;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.profesor = profesor;
		this.diaHora = diaHora;
		this.duracionMin = duracionMin;
		this.estado = estado;
	}


	public int getIdClase() {
		return idClase;
	}


	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public Usuario getProfesor() {
		return profesor;
	}


	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}


	public LocalDateTime getDiaHora() {
		return diaHora;
	}


	public void setDiaHora(LocalDateTime diaHora) {
		this.diaHora = diaHora;
	}


	public int getDuracionMin() {
		return duracionMin;
	}


	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}


	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", nombre=" + nombre + ", capacidad=" + capacidad + ", profesor="
				+ profesor + ", diaHora=" + diaHora + ", duracionMin=" + duracionMin + ", estado=" + estado + "]";
	}
    
    
    
}

