package cl.rhacs.detta.modelos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cl.rhacs.detta.Utilidades;

public class Conexion {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Instancia de la clase {@link Conexion} */
    private static Conexion instancia = null;

    /** Objeto {@link Connection} con la conexión a la base de datos */
    private Connection conexion = null;

    /** URL de conexión con la base de datos */
    private String url;

    /** Nombre de usuario para el acceso a la base de datos */
    private String username;

    /** Contraseña de acceso para el usuario */
    private String password;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link Conexion}
     * 
     * @param url      url de conexión con base de datos
     * @param username nombre de usuario de acceso
     * @param password contraseña para el usuario
     */
    private Conexion(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Devuelve una nueva instancia o la que ya existe del objeto {@link Conexion}
     * 
     * @param url      url de conexión con base de datos
     * @param username nombre de usuario
     * @param password
     * @return
     */
    public static Conexion getInstance(String url, String username, String password) {
        if (instancia == null) {
            instancia = new Conexion(url, username, password);
        }

        return instancia;
    }

    /**
     * Efectúa la conexión con la base de datos
     * 
     * @return un objeto {@link Connection} con la conexión
     */
    public Connection conectar() {
        try {
            // Verificar que el objeto no exista o que la conexión esté cerrada
            if (conexion == null || conexion.isClosed()) {
                // Cargar el driver de Oracle para efectuar la conexión
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Crear nueva conexión
                conexion = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Utilidades.extraerError("Conexion", "conectar", e);
        }

        return conexion;
    }

    /**
     * Desconecta el objeto de la base de datos
     */
    public void desconectar() {
        try {
            // Verificar que el objeto exista y que la conexión no esté cerrada
            if (conexion != null && !conexion.isClosed()) {
                // Cerrar conexión
                conexion.close();
            }
        } catch (SQLException e) {
            Utilidades.extraerError("Conexion", "desconectar", e);
        }
    }

}
