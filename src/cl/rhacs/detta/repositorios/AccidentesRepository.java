package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.ConexionDB;
import cl.rhacs.detta.repositorios.interfaces.IRepository;

public class AccidentesRepository implements IRepository {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link ConexionDB} con la conexión a la base de datos */
    private ConexionDB conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link AccidentesRepository}
     * 
     * @param url      url de conexión con la base de datos
     * @param username nombre de usuario de acceso a la base de datos
     * @param password contraseña de acceso a la base de datos
     */
    public AccidentesRepository(String url, String username, String password) {
        conexion = ConexionDB.getInstance(url, username, password);
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información del error {@link SQLException} y lo muestra por consola
     * 
     * @param e objeto {@link SQLException} con la información del error
     */
    private void extraerExcepcion(SQLException e) {
        // Obtener el código de error
        String codigo = e.getSQLState();

        // Obtener el mensaje de error
        String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

        // Mostrar error por consola
        System.err.println("Código de error SQL: " + codigo);
        System.err.println(mensaje);
    }

    @Override
    public boolean insertarRegistro(Object object) {
        // Conectar con la base de datos
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Armar la consulta
            String sql = "INSERT INTO accidentes (fecha, hora, direccion, circunstancia, lugar, "
                    + " detalles, clasificacion, tipo, medio_prueba, empleador_id, trabajador_id,"
                    + " creado, actualizado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                // Convertir el objeto
                Accidente accidente = (Accidente) object;

                // Preparar la consulta
                PreparedStatement statement = con.prepareStatement(sql);

                // Agregar fecha
                statement.setString(1, accidente.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                // Agregar hora
                statement.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                // Agregar dirección
                statement.setString(3, accidente.getDireccion());
                // Agregar circunstancia
                statement.setString(4, accidente.getCircunstancia());
                // Agregar lugar
                statement.setString(5, accidente.getLugar());
                // Agregar detalles
                statement.setString(6, accidente.getDetalles());
                // Agregar clasificacion
                statement.setString(7, accidente.getClasificacion());
                // Agregar tipo
                statement.setString(8, accidente.getTipo());
                // Agregar medio de prueba
                statement.setString(9, accidente.getMedioPrueba());
                // Agregar relación con el id del empleador
                statement.setInt(10, accidente.getEmpleador().getId());
                // Agregar relación con el id del trabajador
                statement.setInt(11, accidente.getTrabajador().getId());
                // Agregar la fecha y hora de creación
                statement.setString(12, accidente.getCreado().toString());
                // Agregar la fecha y hora de actualización
                statement.setString(13, accidente.getActualizado().toString());

                // Verificar si el registro fue agregado
                boolean fueAgregado = statement.executeUpdate() > 0;

                // Cerrar consulta y conexión
                statement.close();
                conexion.desconectar();

                // Devolver resultado
                return fueAgregado;
            } catch (SQLException e) {
                extraerExcepcion(e);
            }
        } else {
            // No hubo conexión con la base de datos
            System.err.println("No hubo conexión con la base de datos. Por favor, revise los registros");
        }

        return false;
    }

    @Override
    public List<Object> buscarTodos() {
        return null;
    }

    @Override
    public Object buscarPorID(int id) {
        return null;
    }

    @Override
    public boolean actualizarRegistro(Object object) {
        return false;
    }

    @Override
    public boolean eliminarRegistro(Object object) {
        return false;
    }

}
