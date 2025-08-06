package modulo3.modelo.entidad;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {
    private int idReserva;
    private Usuario usuario;
    private Clase clase;
    private LocalDateTime fechaReserva;
    private String estado;  // debe ser enum, cancelado, reservado

    //es cancelable si hay 6 horas o mas de diferencia con la hora actual
    public boolean esCancelable() {
    	LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaClase = clase.getDiaHora();

        Duration diferencia = Duration.between(ahora, fechaClase);
        return diferencia.toHours() >= 6;
    }

	public Reserva(int idReserva, Usuario usuario, Clase clase, LocalDateTime fechaReserva, String estado) {
		super();
		this.idReserva = idReserva;
		this.usuario = usuario;
		this.clase = clase;
		this.fechaReserva = fechaReserva;
		this.estado = estado;
	}

	public Reserva() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", usuario=" + usuario + ", clase=" + clase + ", fechaReserva="
				+ fechaReserva + ", estado=" + estado + "]";
	}
    
    
}
