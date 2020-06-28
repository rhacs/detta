package cl.rhacs.detta.modelos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Instancia de la clase {@link ConexionDB} */
    private static ConexionDB instancia = null;

    /** Objeto {@link Connection} con la conexión a la base de datos */
    private Connection conexion = null;

    /** URL de conexión con la base de datos */
    private String url;

    /** Nombre de usuario de acceso a la base de datos */
    private String username;

    /** Contraseña de acceso a la base de datos */
    private String password;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ConexionDB}
     * 
     * @param url      url de la conexión a la base de datos
     * @param username nombre de usuario de acceso a la base de datos
     * @param password contraseña de acceso a la base de datos
     */
    private ConexionDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información de la {@link SQLException} y la muestra por consola
     * 
     * @param e objeto {@link SQLException} con la información de la excepción
     */
    private void extraerExcepcion(SQLException e) {
        // Obtener el código de error
        String codigo = e.getSQLState();

        // Obtener el mensaje de error
        String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

        // Mostrar error por consola
        System.err.println(" [!] Código de error SQL: " + codigo);
        System.err.println(" [!] " + mensaje);
    }

    /**
     * Método estático que crea una nueva instancia de la clase {@link ConexionDB}.
     * Si se creó una instancia con anterioridad, devuelve ésta y no una nueva
     * 
     * @param url      url de conexión con la base de datos
     * @param username nombre de usuario de acceso a la base de datos
     * @param password contraseña de acceso a la base de datos
     * @return una instancia de la clase {@link ConexionDB}
     */
    public static ConexionDB getInstance(String url, String username, String password) {
        if (instancia == null) {
            instancia = new ConexionDB(url, username, password);
        }

        return instancia;
    }

    /**
     * Efectúa la conexión con la base de datos
     * 
     * @return objeto {@link Connection} con la conexión a la base de datos,
     *         {@code null} en cualquier otro caso
     */
    public Connection conectar() {
        try {
            // Verificar que el objeto conexión no exista o que la conexión esté cerrada
            if (conexion == null || conexion.isClosed()) {
                // Crear una nueva conexión
                conexion = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            // Ocurrió un error
            System.err.println("ERROR ConexionDB#conectar()");
            extraerExcepcion(e);
        }

        return conexion;
    }

    /**
     * Efectúa la desconexión con la base de datos
     */
    public void desconectar() {
        try {
            // Verificar que el objeto exista y la conexión no esté cerrada
            if (conexion != null && !conexion.isClosed()) {
                // Cerrar conexión
                conexion.close();

                // "Eliminar" objeto
                conexion = null;
            }
        } catch (SQLException e) {
            // Ocurrió un error
            System.err.println("ERROR ConexionDB#desconectar()");
            extraerExcepcion(e);
        }
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la url de conexión con la base de datos
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return el nombre de usuario de acceso a la base de datos
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return la contraseña de acceso a la base de datos
     */
    public String getPassword() {
        return password;
    }

    // Herencias (Objeto)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        ConexionDB other = (ConexionDB) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;

        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;

        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public String toString() {
        return "ConexionDB [url=" + url + ", username=" + username + ", password=" + password + "]";
    }

}
