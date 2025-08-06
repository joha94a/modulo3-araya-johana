package modulo3.modelo.dto;

import java.time.LocalDateTime;

import modulo3.modelo.entidad.Clase;
import modulo3.modelo.entidad.Usuario;

public class ReservaDTO {
	private int idReserva;
    private Usuario usuario;
    private Clase clase;
    private LocalDateTime fechaReserva;
    private String estado;
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
}
