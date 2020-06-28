package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.ConexionDB;
import cl.rhacs.detta.modelos.enums.EClasificacion;
import cl.rhacs.detta.modelos.enums.EPrueba;
import cl.rhacs.detta.modelos.enums.ETipo;
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
            String sql = "INSERT INTO accidentes (fecha, hora, direccion, comuna, circunstancia,"
                    + " lugar, detalles, clasificacion, tipo, medio_prueba, creado, actualizado)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                // Agregar comuna
                statement.setString(4, accidente.getComuna());
                // Agregar circunstancia
                statement.setString(5, accidente.getCircunstancia());
                // Agregar lugar
                statement.setString(6, accidente.getLugar());
                // Agregar detalles
                statement.setString(7, accidente.getDetalles());
                // Agregar clasificacion
                statement.setString(8, accidente.getClasificacion());
                // Agregar tipo
                statement.setString(9, accidente.getTipo());
                // Agregar medio de prueba
                statement.setString(10, accidente.getMedioPrueba());

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
            System.err.println("No hubo conexión con la base de datos. Revise los registros.");
        }

        // Devolver falso si ocurrió algún error durante el proceso de inserción
        return false;
    }

    @Override
    public List<Object> buscarTodos() {
        return null;
    }

    @Override
    public Object buscarPorID(int id) {
        // Crear el objeto Accidente
        Accidente accidente = null;

        // Conectar con la base de datos
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Armar consulta
            String sql = "SELECT id, fecha, hora, direccion, circunstancia, lugar, detalles,"
                    + " clasificacion, tipo, medio_prueba, empleador_id, trabajador_id, creado,"
                    + "actualizado FROM accidentes WHERE id = ? LIMIT 1";

            try {
                // Preparar consulta
                PreparedStatement statement = con.prepareStatement(sql);

                // Insertar identificador
                statement.setInt(1, id);

                // Ejecutar consulta
                ResultSet resultado = statement.executeQuery();

                // Verificar si hay resultados
                if (resultado.next()) {
                    // Crear el objeto Accidente
                    accidente = new Accidente();

                    // Llenar el objeto con los valores del resultado
                    accidente.setId(id);
                    accidente.setFecha(LocalDate.parse(resultado.getString("fecha")));
                    accidente.setHora(LocalTime.parse(resultado.getString("hora")));
                    accidente.setDireccion(resultado.getString("direccion"));
                    accidente.setCircunstancia(resultado.getString("circunstancia"));
                    accidente.setLugar(resultado.getString("lugar"));
                    accidente.setDetalles(resultado.getString("detalles"));
                    accidente.setClasificacion(EClasificacion.valueOf(resultado.getString("clasificacion")));
                    accidente.setTipo(ETipo.valueOf(resultado.getString("tipo")));
                    accidente.setMedioPrueba(EPrueba.valueOf(resultado.getString("medio_prueba")));
                } else {
                    System.err.println("No existe el registro dentro de la tabla ACCIDENTES con ID = " + id);
                }
            } catch (SQLException e) {
                extraerExcepcion(e);
            }
        } else {
            // No hubo conexión
            System.err.println("No hubo conexión con la base de datos. Revise los registros.");
        }

        return accidente;
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
