package modulo3.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
	
    private String nombreUsuario;
    private String email;
    private String contraseña;
    private String rol; // deberia ser Enum 
    private boolean estado;
    
 // Constructor por defecto requerido por JPA
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String email, String contraseña, String rol, boolean estado) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contraseña = contraseña;
		this.rol = rol;
		this.estado = estado;
	}



	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}



	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}



	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public boolean esAdministrador() {
        return this.rol.equals("Admin");
    }



	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", email=" + email
				+ ", contraseña=" + contraseña + ", rol=" + rol + ", estado=" + estado + "]";
	}
	
	
    
}

