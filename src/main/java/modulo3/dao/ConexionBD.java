package modulo3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/modulo3";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

    // Constructor privado para que no se instancie
    private ConexionBD() {}

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }
}
