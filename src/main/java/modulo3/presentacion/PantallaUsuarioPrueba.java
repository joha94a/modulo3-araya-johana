package modulo3.presentacion;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modulo3.dao.*;
import modulo3.modelo.entidad.Usuario;
import modulo3.negocio.IUsuarioService;
import modulo3.negocio.UsuarioServiceImpl;

public class PantallaUsuarioPrueba {
    private final Scanner scanner = new Scanner(System.in);
    private IUsuarioService usuarioService;
    private SessionFactory sessionFactory;

    public void iniciar() {
        System.out.println("¿Qué implementación querés usar?");
        System.out.println("1 - JDBC");
        System.out.println("2 - JPA");
        System.out.println("3 - Hibernate");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        UsuarioDAO usuarioDAO = null;

        switch (opcion) {
        case 1:
            usuarioDAO = new UsuarioDAOJDBC();
            break;
        case 2:
            usuarioDAO = new UsuarioDAOJPA();
            break;
        case 3:
            sessionFactory = new Configuration().configure().buildSessionFactory();
            usuarioDAO = new UsuarioDAOHibernate(sessionFactory);
            break;
        default:
            System.out.println("Opción inválida.");
            return;
    }

        usuarioService = new UsuarioServiceImpl(usuarioDAO);
        ejecutarMenu();
        if (sessionFactory != null) sessionFactory.close();
    }

    private void ejecutarMenu() {
        System.out.print("Ingresá el ID del usuario que querés buscar: ");
        int id = scanner.nextInt();

        try {
            Usuario usuario = usuarioService.obtenerPorId(id);
            if (usuario != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + usuario.getIdUsuario());
                System.out.println("Nombre: " + usuario.getNombreUsuario());
                System.out.println("Email: " + usuario.getEmail());
            } else {
                System.out.println("No se encontró el usuario con ID " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}