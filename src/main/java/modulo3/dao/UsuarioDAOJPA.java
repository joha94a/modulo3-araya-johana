package modulo3.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modulo3.modelo.entidad.Usuario;

public class UsuarioDAOJPA implements UsuarioDAO{
	
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");


	@Override
	public void guardar(Usuario user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Usuario user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario listarPorId(int id) throws SQLException {
		EntityManager em = emf.createEntityManager();
		Usuario usuario = null;
		    try {
		        usuario = em.find(Usuario.class, id);
		    } finally {
		        em.close();
		    }
		    return usuario;
	}

}
