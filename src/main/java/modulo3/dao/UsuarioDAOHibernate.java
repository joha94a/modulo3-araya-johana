package modulo3.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modulo3.modelo.entidad.Usuario;

public class UsuarioDAOHibernate implements UsuarioDAO{
	
	private SessionFactory sessionFactory;

    public UsuarioDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
	    Session session = sessionFactory.openSession();
	    Usuario usuario = null;
	    try {
	        usuario = session.get(Usuario.class, id);  // Busca por clave primaria
	    } finally {
	        session.close();
	    }
	    return usuario;
	}

}
